import com.example.untitled.dto.UserRegistrationDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegistrationDTOTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        UserRegistrationDTO userDTO = new UserRegistrationDTO();
        String username = "testUser";
        String password = "testPassword";

        // Act
        userDTO.setUsername(username);
        userDTO.setPassword(password);

        // Assert
        assertEquals(username, userDTO.getUsername());
        assertEquals(password, userDTO.getPassword());
    }
}
