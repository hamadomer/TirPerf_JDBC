package repositoryTests;

import org.example.model.Applicatif;
import org.example.repository.ApplicatifRepository;
import org.junit.jupiter.api.*;
import java.sql.SQLException;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

public class ApplicatifRepositoryTest {

    private ApplicatifRepository repository;
    private Applicatif applicatif;

    @BeforeEach
    public void setup() throws Exception {
        repository = ApplicatifRepository.getInstance();
        applicatif = new Applicatif("Test Applicatif", "1.0", "Function 1");
        Optional<Integer> generatedId = repository.createApplicatif(applicatif);
        applicatif.setId(generatedId.orElseThrow(()->new RuntimeException("Applicatif could not be created")));
    }

    @AfterEach
    public void tearDown() throws SQLException {
        repository.connection.prepareStatement("DELETE FROM applicatif").executeUpdate();
    }

    @Test
    public void testCreateApplicatif() throws SQLException {
        Optional<Applicatif> createdApplicatif = repository.findApplicatifById(applicatif.getId());
        assertTrue(createdApplicatif.isPresent());
        assertEquals(applicatif.getIntitule(), createdApplicatif.get().getIntitule());
        assertEquals(applicatif.getVersion(), createdApplicatif.get().getVersion());
        assertEquals(applicatif.getFonctions(), createdApplicatif.get().getFonctions());
    }

    @Test
    public void testFindApplicatifById_NotFound() {
        Optional<Applicatif> notFoundApplicatif = repository.findApplicatifById(999);
        assertFalse(notFoundApplicatif.isPresent());
    }
}