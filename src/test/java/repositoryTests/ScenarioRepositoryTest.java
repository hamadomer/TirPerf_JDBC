package repositoryTests;

import org.example.model.Applicatif;
import org.example.model.Scenario;
import org.example.repository.ScenarioRepository;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ScenarioRepositoryTest {

    private ScenarioRepository repository;
    private Scenario scenario;

    @BeforeEach
    public void setUp() throws Exception {
        repository = ScenarioRepository.getInstance();
        scenario = new Scenario("Some description", 1, "Some fonction");
        Optional<Integer> generatedId = repository.createScenario(scenario);
        scenario.setId(generatedId.orElseThrow(()-> new RuntimeException("Scenario could not be created")));
    }

    @AfterEach
    public void tearDown() throws SQLException {
        repository.connection.prepareStatement("DELETE FROM scenario").executeUpdate();
    }

    @Test
    public void testCreateScenario() throws SQLException {
        Optional<Scenario> createdScenario = repository.findScenarioById(scenario.getId());
        assertTrue(createdScenario.isPresent());
        assertEquals(scenario.getDescription(), createdScenario.get().getDescription());
        assertEquals(scenario.getApplicatif_id(), createdScenario.get().getApplicatif_id());
        assertEquals(scenario.getScenario_fonctions(), createdScenario.get().getScenario_fonctions());
    }

    @Test
    public void testFindScenarioById_NotFound() throws SQLException {
        Optional<Scenario> notFoundApplicatif = repository.findScenarioById(999);
        assertFalse(notFoundApplicatif.isPresent());
    }
}
