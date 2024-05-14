import com.example.untitled.domain.User;
import com.example.untitled.dto.UserRegistrationDTO;
import com.example.untitled.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.untitled.controller.RegistrationController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegistrationControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegistrationController registrationController;

    @Test
    void testRegisterUser_SuccessfulRegistration() {
        // Arrange
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUsername("testuser");
        userRegistrationDTO.setPassword("testpassword");

        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // Act
        ResponseEntity<String> responseEntity = registrationController.registerUser(userRegistrationDTO);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User registered successfully", responseEntity.getBody());

        verify(userRepository, times(1)).existsByUsername("testuser");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_UserAlreadyExists() {
        // Arrange
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUsername("existinguser");
        userRegistrationDTO.setPassword("testpassword");

        when(userRepository.existsByUsername("existinguser")).thenReturn(true);

        // Act
        ResponseEntity<String> responseEntity = registrationController.registerUser(userRegistrationDTO);

        // Assert
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Username already exists", responseEntity.getBody());

        verify(userRepository, times(1)).existsByUsername("existinguser");
        verify(userRepository, never()).save(any(User.class));
    }
}
