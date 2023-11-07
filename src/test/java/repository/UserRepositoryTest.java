package repository;

import entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//jk@DataJpaTest
//@ActiveProfiles("test") // If you have a specific profile for testing
@SpringBootTest
public class UserRepositoryTest {

//    @Autowired
//    private TestEntityManager entityManager;

    @Mock
    private UserRepository userRepository;

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // given
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
//        entityManager.persist(user);
//        entityManager.flush();

        // when
        Optional<User> found = userRepository.findByUsername(user.getUsername());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void whenFindByUsername_thenReturnEmpty() {
        // given no user

        // when
        Optional<User> found = userRepository.findByUsername("nonExistentUser");

        // then
        assertThat(found).isNotPresent();
    }
}
