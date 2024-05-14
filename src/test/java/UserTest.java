import com.example.untitled.domain.User;
import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUsernameValidation() {
        User user = new User();
        assertThrows(IllegalArgumentException.class, () -> user.setUsername(null));
        assertThrows(IllegalArgumentException.class, () -> user.setUsername(""));
        assertThrows(IllegalArgumentException.class, () -> user.setUsername(" "));
        assertThrows(IllegalArgumentException.class, () -> user.setUsername("username with space"));
        user.setUsername("validUsername");
        assertEquals("validUsername", user.getUsername());
    }

    @Test
    public void testPasswordValidation() {
        User user = new User();
        assertThrows(IllegalArgumentException.class, () -> user.setPassword(null));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword(""));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword(" "));
        assertThrows(IllegalArgumentException.class, () -> user.setPassword("password with space"));
        user.setPassword("validPassword");
        assertEquals("validPassword", user.getPassword());
    }

    @Test
    public void testEmailValidation() {
        User user = new User();
        assertDoesNotThrow(() -> user.setEmail(null));
        assertDoesNotThrow(() -> user.setEmail(""));
        assertDoesNotThrow(() -> user.setEmail(" "));
        assertDoesNotThrow(() -> user.setEmail("invalidEmail")); // It's not enforced in the setter
        assertDoesNotThrow(() -> user.setEmail("valid@example.com"));
        assertEquals("valid@example.com", user.getEmail());
    }

    @Test
    public void testNameValidation() {
        User user = new User();
        user.setName(null);
        assertNull(user.getName());
        user.setName("");
        assertNull(user.getName());
        user.setName("validName");
        assertEquals("validName", user.getName());
    }

    @Test
    public void testPhoneNumberValidation() {
        User user = new User();
        assertDoesNotThrow(() -> user.setPhoneNumber(null));
        assertDoesNotThrow(() -> user.setPhoneNumber(""));
        assertDoesNotThrow(() -> user.setPhoneNumber("+380982736476")); // A valid example
        assertEquals("+380982736476", user.getPhoneNumber());
    }

    @Test
    public void testBirthdayValidation() {
        User user = new User();
        user.setB_day(null);
        assertNull(user.getB_day());
        user.setB_day(LocalDate.now());
        user.setB_day(LocalDate.of(2000, 1, 1));
        assertEquals(LocalDate.of(2000, 1, 1), user.getB_day());
    }

    @Test
    public void testInfoAboutMe() {
        User user = new User();
        user.setInfo_about_me("Some info");
        assertEquals("Some info", user.getInfo_about_me());
    }

    @Test
    public void testLocation() {
        User user = new User();
        user.setLocation("Some location");
        assertEquals("Some location", user.getLocation());
    }

    @Test
    public void testAvatar() {
        User user = new User();
        user.setAvatar("Some avatar");
        assertEquals("Some avatar", user.getAvatar());
    }
}
