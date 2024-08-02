package repositoryTests;

import org.example.Helpers.FlywayExtension;
import org.example.model.PanSI;
import org.example.model.Scenario;
import org.example.repository.PanSIRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(FlywayExtension.class)
public class PanSIRepositoryTests {
    private PanSIRepository repository;
    private PanSI panSI;

    @BeforeEach
    public void setUp() throws SQLException {
        repository = PanSIRepository.getInstance();
//        repository.deleteAllPansi();
        panSI = new PanSI();
        Optional<Integer> generatedId = repository.createPanSI(panSI);
        panSI.setId(generatedId.orElseThrow(()-> new RuntimeException("PanSI id not created")));
    }

    @Test
    public void testCreatePanSI() throws SQLException {
        Optional<PanSI> createdPanSI = repository.findPanSIById(panSI.getId());
        assertTrue(createdPanSI.isPresent());
        assertEquals(panSI.getId(), createdPanSI.get().getId());
    }

    @Test
    public void testFindPanSIById_NotFound() throws SQLException {
        Optional<PanSI> notFoundApplicatif = repository.findPanSIById(999);
        assertFalse(notFoundApplicatif.isPresent());
    }
}
