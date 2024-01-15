package Controller;

import com.project.GraduationProcessManagementApplication;
import com.project.controller.ManagementSystemController;
import com.project.entity.Thesis;
import com.project.service.ManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = GraduationProcessManagementApplication.class)
public class ManagementSystemControllerTest {

    @Mock
    private ManagementService managementService;

    @InjectMocks
    private ManagementSystemController managementSystemController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Thesis sampleThesis;

    @BeforeEach
    public void setup() throws ParseException {
        mockMvc = MockMvcBuilders.standaloneSetup(managementSystemController).build();


        sampleThesis = new Thesis();
        sampleThesis.setTitle("Sample Thesis Title");
        sampleThesis.setObjective("Objective of the thesis");
        sampleThesis.setTasks("List of tasks");
        sampleThesis.setTechnologies("Used technologies");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date submissionDate = dateFormat.parse("2023-01-01");
        sampleThesis.setSubmissionDate(submissionDate);
    }

    @Test
    @WithMockUser(username = "student", roles = {"STUDENT"})
    public void whenUploadThesis_thenReturnsThesis() throws Exception {
        given(managementService.uploadThesis(any(Thesis.class))).willReturn(sampleThesis);

        mockMvc.perform(post("/api/management-system/upload-thesis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sampleThesis)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Thesis Title"));
    }

    @Test
    @WithMockUser(username = "teacher", roles = {"TEACHER"})
    public void whenProcessThesis_thenSuccess() throws Exception {
        given(managementService.processThesis(any(Thesis.class))).willReturn(null); // Assuming void return

        mockMvc.perform(post("/api/management-system/process-thesis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleThesis)))
                .andExpect(status().isOk());
    }

}
