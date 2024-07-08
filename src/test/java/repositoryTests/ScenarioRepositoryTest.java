package repositoryTests;

import org.example.Helpers.FlywayExtension;
import org.example.model.Scenario;
import org.example.repository.ScenarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(FlywayExtension.class)
public class ScenarioRepositoryTest {

    private ScenarioRepository repository;
    private Scenario scenario;

    @BeforeEach
    public void setUp() throws Exception {
        repository = ScenarioRepository.getInstance();
        scenario = new Scenario("Some description", 1);
        Optional<Integer> generatedId = repository.createScenario(scenario);
        scenario.setId(generatedId.orElseThrow(()-> new RuntimeException("Scenario could not be created")));
    }

//    @AfterEach
//    public void tearDown() throws SQLException {
//        repository.connection.prepareStatement("DELETE FROM scenario").executeUpdate();
//    }

    @Test
    public void testCreateScenario() throws SQLException {
        Optional<Scenario> createdScenario = repository.findScenarioById(scenario.getId());
        assertTrue(createdScenario.isPresent());
        assertEquals(scenario.getDescription(), createdScenario.get().getDescription());
        assertEquals(scenario.getApplicatif_id(), createdScenario.get().getApplicatif_id());
    }

    @Test
    public void testFindScenarioById_NotFound() throws SQLException {
        Optional<Scenario> notFoundApplicatif = repository.findScenarioById(999);
        assertFalse(notFoundApplicatif.isPresent());
    }
}
