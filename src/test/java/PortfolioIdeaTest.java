import com.example.untitled.domain.PortfolioIdea;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PortfolioIdeaTest {

    @Test
    public void testDefaultConstructor() {
        // Arrange
        PortfolioIdea portfolioIdea = new PortfolioIdea();

        // Assert
        assertNotNull(portfolioIdea);
        assertNotNull(portfolioIdea.getPublicationDate());
        assertEquals(LocalDate.now(), portfolioIdea.getPublicationDate());
    }

    @Test
    public void testParameterizedConstructor() {
        // Arrange
        String username = "testUser";
        String ideaTitle = "Test Idea";
        String description = "This is a test idea";
        List<String> photoUrls = Arrays.asList("url1", "url2");
        String p_i = "p";

        // Act
        PortfolioIdea portfolioIdea = new PortfolioIdea(username, ideaTitle, description, photoUrls,p_i);

        // Assert
        assertNotNull(portfolioIdea);
        assertEquals(username, portfolioIdea.getUserId());
        assertEquals(ideaTitle, portfolioIdea.getIdeaTitle());
        assertEquals(description, portfolioIdea.getDescription());
        assertEquals(photoUrls, portfolioIdea.getPhotoUrls());
        assertNotNull(portfolioIdea.getPublicationDate());
        assertEquals(LocalDate.now(), portfolioIdea.getPublicationDate());
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        PortfolioIdea portfolioIdea = new PortfolioIdea();
        String username = "testUser";
        String ideaTitle = "Test Idea";
        String description = "This is a test idea";
        LocalDate publicationDate = LocalDate.of(2022, 4, 1);
        List<String> photoUrls = Arrays.asList("url1", "url2");

        // Act
        portfolioIdea.setUserId(username);
        portfolioIdea.setIdeaTitle(ideaTitle);
        portfolioIdea.setDescription(description);
        portfolioIdea.setPublicationDate(publicationDate);
        portfolioIdea.setPhotoUrls(photoUrls);

        // Assert
        assertEquals(username, portfolioIdea.getUserId());
        assertEquals(ideaTitle, portfolioIdea.getIdeaTitle());
        assertEquals(description, portfolioIdea.getDescription());
        assertEquals(publicationDate, portfolioIdea.getPublicationDate());
        assertEquals(photoUrls, portfolioIdea.getPhotoUrls());
    }
}
