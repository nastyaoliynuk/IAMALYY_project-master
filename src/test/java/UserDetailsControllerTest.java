import com.example.untitled.controller.UserDetailsController;
import com.example.untitled.domain.User;
import com.example.untitled.repos.UserRepository;
import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserDetailsControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsController userDetailsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateProfile_Success() throws NumberParseException {
        // Arrange
        User existingUser = new User();
        existingUser.setUsername("testUser");
        existingUser.setPassword("testPassword");
        existingUser.setPhoneNumber("+380981234567");
        existingUser.setEmail("test@example.com");
        existingUser.setName("Test User");
        existingUser.setInfo_about_me("Test info");
        existingUser.setLocation("Test location");
        existingUser.setAvatar("test_avatar.jpg");
        existingUser.setStatus(User.UserStatus.NONAME);
        existingUser.setB_day(LocalDate.of(1990, 1, 1));

        User updatedUser = new User();
        updatedUser.setUsername("testUser");
        updatedUser.setPassword("testPassword");
        updatedUser.setPhoneNumber("+380981234563"); // New phone number
        updatedUser.setEmail("updated@example.com");
        updatedUser.setName("Updated Name");
        updatedUser.setInfo_about_me("Updated info");
        updatedUser.setLocation("Updated location");
        updatedUser.setAvatar("updated_avatar.jpg");
        updatedUser.setStatus(User.UserStatus.MODEL);
        updatedUser.setB_day(LocalDate.of(1995, 5, 5));

        when(userRepository.findByUsernameAndPassword("testUser", "testPassword")).thenReturn(existingUser);

        // Act
        ResponseEntity<String> response = userDetailsController.updateProfile(updatedUser);

        // Assert
        verify(userRepository, times(1)).save(existingUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Profile updated successfully", response.getBody());
        assertEquals("+380981234563", existingUser.getPhoneNumber());
        assertEquals("updated@example.com", existingUser.getEmail());
        assertEquals("Updated Name", existingUser.getName());
        assertEquals("Updated info", existingUser.getInfo_about_me());
        assertEquals("Updated location", existingUser.getLocation());
        assertEquals("updated_avatar.jpg", existingUser.getAvatar());
        assertEquals(User.UserStatus.MODEL, existingUser.getStatus());
        assertEquals(LocalDate.of(1995, 5, 5), existingUser.getB_day());
    }

    @Test
    public void testUpdateProfile_UserNotFound() throws NumberParseException {
        // Arrange
        User updatedUser = new User();
        updatedUser.setUsername("testUserNotFound");
        updatedUser.setPassword("testPasswordNotFound");

        when(userRepository.findByUsernameAndPassword("testUserNotFound", "testPasswordNotFound")).thenReturn(null);

        // Act
        ResponseEntity<String> response = userDetailsController.updateProfile(updatedUser);

        // Assert
        verify(userRepository, never()).save(any());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    public void testUpdateProfile_InvalidPhoneNumber() throws NumberParseException {
        // Arrange
        User existingUser = new User();
        existingUser.setUsername("testUser");
        existingUser.setPassword("testPassword");

        User updatedUser = new User();
        updatedUser.setUsername("testUser");
        updatedUser.setPassword("testPassword");
        updatedUser.setPhoneNumber("invalidPhoneNumber");

        when(userRepository.findByUsernameAndPassword("testUser", "testPassword")).thenReturn(existingUser);

        // Act
        ResponseEntity<String> response = userDetailsController.updateProfile(updatedUser);


    }
}
