package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.GraduationProcessManagementApplication;
import com.project.controller.AuthController;
import com.project.entity.Student;
import com.project.entity.Teacher;
import com.project.entity.User;
import com.project.repository.UserRepository;
import com.project.dto.RegistrationDto;
import com.project.dto.LoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = GraduationProcessManagementApplication.class)
public class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void whenRegisterUser_thenReturnsUser() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUsername("testUser");
        registrationDto.setPassword("password");
        registrationDto.setRole("STUDENT");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationDto)))
                .andExpect(status().isOk());

        verify(userRepository).save(any(User.class));
    }

    @Test
    public void whenRegisterExistingUser_thenBadRequest() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUsername("existingUser");
        registrationDto.setPassword("password");
        registrationDto.setRole("TEACHER");

        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(new Teacher()));

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenLoginWithCorrectCredentials_thenReturnsUser() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testUser");
        loginDto.setPassword("password");

        User user = new
                Student();
        user.setUsername("testUser");
        user.setPassword(new AuthController().encodeBase64("password"));

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(user)));
    }

    @Test
    public void whenLoginWithIncorrectPassword_thenUnauthorized() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testUser");
        loginDto.setPassword("wrongPassword");

        User user = new Student();
        user.setUsername("testUser");
        user.setPassword(new AuthController().encodeBase64("password"));

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void whenLoginNonExistentUser_thenBadRequest() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("nonExistentUser");
        loginDto.setPassword("password");

        when(userRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isBadRequest());
    }
}