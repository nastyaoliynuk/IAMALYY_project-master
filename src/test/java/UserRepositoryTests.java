import com.example.untitled.domain.User;
import com.example.untitled.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTests {

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        // Arrange
        User expectedUser = new User();
        expectedUser.setUsername("testUser");
        expectedUser .setPassword("password");
        when(userRepository.findByUsername("testUser")).thenReturn(expectedUser);

        // Act
        User actualUser = userRepository.findByUsername("testUser");

        // Assert
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testFindByUsernameAndPassword() {
        // Arrange
        User expectedUser = new User();
        expectedUser.setUsername("testUser");
        expectedUser .setPassword("password");
        when(userRepository.findByUsernameAndPassword("testUser", "password")).thenReturn(expectedUser);

        // Act
        User actualUser = userRepository.findByUsernameAndPassword("testUser", "password");

        // Assert
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testExistsByUsername() {
        // Arrange
        when(userRepository.existsByUsername("testUser")).thenReturn(true);

        // Act
        boolean userExists = userRepository.existsByUsername("testUser");

        // Assert
        assertEquals(true, userExists);
    }
}
