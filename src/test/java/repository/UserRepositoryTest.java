package repository;

import com.project.GraduationProcessManagementApplication;
import com.project.entity.User;
import com.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = GraduationProcessManagementApplication.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setPassword("password");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());
    }

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // action
        Optional<User> found = userRepository.findByUsername(user.getUsername());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void whenFindByUsername_thenReturnEmpty() {
        // when
        Optional<User> found = userRepository.findByUsername("nonExistentUser");

        // then
        assertThat(found).isNotPresent();
    }
}
