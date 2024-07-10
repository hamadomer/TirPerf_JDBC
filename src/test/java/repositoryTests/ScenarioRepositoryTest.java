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

    @Test
    public void testCreateScenario() throws SQLException {
        Optional<Scenario> createdScenario = repository.findScenarioById(scenario.getId());
        assertTrue(createdScenario.isPresent());
        assertEquals(scenario.getDescription(), createdScenario.get().getDescription());
        assertEquals(scenario.getApplicatif_id(), createdScenario.get().getApplicatif_id());
    }

    @Test
    public void testUpdateScenario() throws SQLException {
        scenario.setDescription("Updated description");
        scenario.setApplicatif_id(3);
        int rowsAffected = repository.updateScenario(scenario);
        assertEquals(rowsAffected, 1);
        Scenario updatedScenario = repository.findScenarioById(scenario.getId()).orElseThrow(()-> new RuntimeException("Scenario could not be found"));
        assertEquals("Updated description", updatedScenario.getDescription());
        assertEquals(3, updatedScenario.getApplicatif_id());
    }

    @Test
    public void testDeleteScenario() throws SQLException {
        int rowsAffected = repository.deleteScenario(scenario.getId());
        assertEquals(1, rowsAffected);
        assertEquals(Optional.empty() ,repository.findScenarioById(scenario.getId()));
    }
}
