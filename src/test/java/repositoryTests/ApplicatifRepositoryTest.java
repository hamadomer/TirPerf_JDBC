package repositoryTests;


import org.example.Helpers.FlywayExtension;
import org.example.model.*;
import org.example.repository.ApplicatifRepository;
import org.example.repository.FonctionRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(FlywayExtension.class)
public class ApplicatifRepositoryTest {

    private ApplicatifRepository repository;
    private Applicatif applicatif;
    private FonctionRepository fonctionRepository;
    private Fonction fonction;

    @BeforeEach
    public void setup() throws Exception {

        repository = ApplicatifRepository.getInstance();
        applicatif = new Applicatif("Test Applicatif", "1.0", 1);
        Optional<Integer> generatedAppId = repository.createApplicatif(applicatif);
        applicatif.setId(generatedAppId.orElseThrow(()->new RuntimeException("Applicatif could not be created")));
        fonctionRepository = FonctionRepository.getInstance();
        fonction = new Fonction("Updated fonction");
        Optional<Integer> generatedFonctionId = fonctionRepository.createFonction(fonction);
        fonction.setId(generatedAppId.orElseThrow(()-> new RuntimeException("Fonction could not be created")));
    }

    @Test
    public void testCreateApplicatif() throws SQLException {
        Optional<Applicatif> createdApplicatif = repository.findApplicatifById(applicatif.getId());
        assertTrue(createdApplicatif.isPresent());
        assertEquals(applicatif.getIntitule(), createdApplicatif.get().getIntitule());
        assertEquals(applicatif.getVersion(), createdApplicatif.get().getVersion());
        assertEquals(applicatif.getFonction(), createdApplicatif.get().getFonction());
    }

    @Test
    public void testFindApplicatifById_NotFound() {
        Optional<Applicatif> notFoundApplicatif = repository.findApplicatifById(999);
        assertFalse(notFoundApplicatif.isPresent());
    }

    @Test
    public void testDeleteApplicatif() throws SQLException {
        int deletedRows = repository.deleteApplicatif(applicatif.getId());
        assertEquals(1, deletedRows);
        assertEquals(Optional.empty(), repository.findApplicatifById(applicatif.getId()));
    }

    @Test
    public void testUpdateApplicatif() throws SQLException {
        int rowsAffected = repository.updateApplicatifFonction(applicatif.getId(), fonction.getId());
        assertEquals(1, rowsAffected);
        Optional<Applicatif> updatedApplicatif = repository.findApplicatifById(applicatif.getId());
        assertTrue(updatedApplicatif.isPresent());
        assertEquals(updatedApplicatif.get().getFonction(), fonction.getId());
    }

    @Test
    public void testFindAllScenarios() throws SQLException{
            List<Scenario> scenario = repository.findAllScenarios(1);
            assertTrue(!scenario.isEmpty());
    }

    @Test
    public void testGetAllTirPerf() throws SQLException{
        List<TirPerf> tirPerfs = repository.getAllTirPerfs(1);
        assertTrue(!tirPerfs.isEmpty());
    }

    @Test
    public void testGetAllRapportExcution() throws SQLException{
        List<RapportExecution> rapportExecutions = repository.getAllRapportExection(2);
        assertTrue(!rapportExecutions.isEmpty());
    }
}