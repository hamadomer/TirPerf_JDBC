package repositoryTests;

import org.example.Helpers.FlywayExtension;
import org.example.model.ContextExecution;
import org.example.repository.ContextExecutionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(FlywayExtension.class)
public class ContextExecutionRepositoryTest {

    private ContextExecutionRepository repository;
    private ContextExecution contextExecution;

    @BeforeEach
    public void setUp() throws SQLException {
        repository = ContextExecutionRepository.getInstance();
        contextExecution = new ContextExecution(1,"env", "info", 1);
        Optional<Integer> generatedId = repository.createContextExecution(contextExecution);
        contextExecution.setId(generatedId.orElseThrow(() -> new RuntimeException("Could not create ContextExecution")));
    }

//    @AfterEach
//    public void tearDown() throws SQLException {
//        repository.connection.prepareStatement("DELETE FROM contextexecution").execute();
//    }

    @Test
    public void testCreateContextExecution() throws SQLException {
        Optional<ContextExecution> createdContextExecution = repository.findContextExecutionById(contextExecution.getId());
        assertTrue(createdContextExecution.isPresent());
        assertEquals(contextExecution.getId(), createdContextExecution.get().getId());
        assertEquals(contextExecution.getPanSiId(), createdContextExecution.get().getPanSiId());
        assertEquals(contextExecution.getEnv(), createdContextExecution.get().getEnv());
        assertEquals(contextExecution.getInfoComplementaire(), createdContextExecution.get().getInfoComplementaire());
        assertEquals(contextExecution.getTirPerfId(), createdContextExecution.get().getTirPerfId());
    }
}