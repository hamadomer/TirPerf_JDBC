package repositoryTests;

import org.example.Helpers.FlywayExtension;
import org.example.model.RapportExecution;
import org.example.repository.RapportExecutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(FlywayExtension.class)
public class RapportExecutionRepositoryTest {
    private RapportExecutionRepository repository;
    private RapportExecution rapportExecution;

    @BeforeEach
    public void setUp() throws SQLException {
        repository = RapportExecutionRepository.getInstance();
        rapportExecution = new RapportExecution(10,80, "2 errors", 30, 3);
        Optional<Integer> generatedId = repository.createRapportEx(rapportExecution);
        rapportExecution.setId(generatedId.orElseThrow(()-> new RuntimeException("could not create rapport execution")));
    }

    @Test
    public void testCreateRapportExecution() throws SQLException {
        Optional<RapportExecution> createdRapoport = repository.findRapportExById(rapportExecution.getId());
        assertTrue(createdRapoport.isPresent());
        assertEquals(10, createdRapoport.get().getCallsNumber());
        assertEquals(80, createdRapoport.get().getSuccessRate());
        assertEquals("2 errors", createdRapoport.get().getErrors());
        assertEquals(3, createdRapoport.get().getTirPerfId());
    }

    @Test
    public void testUpdateRapportExecution() throws SQLException {
        rapportExecution.setCallsNumber(20);
        rapportExecution.setSuccessRate(40);
        rapportExecution.setErrors("12 errors");
        rapportExecution.setTirPerfId(4);
        int rowsAffected = repository.UpdateRapportEx(rapportExecution);
        assertEquals(1, rowsAffected);
        RapportExecution updatedRapport = repository.findRapportExById(rapportExecution.getId()).orElseThrow(()-> new RuntimeException("could not find rapport execution"));
        assertEquals(20, updatedRapport.getCallsNumber());
        assertEquals(40, updatedRapport.getSuccessRate());
        assertEquals("12 errors", updatedRapport.getErrors());
        assertEquals(4, updatedRapport.getTirPerfId());
    }

    @Test
    public void testDeleteRapportExecution() throws SQLException {
        int rowsAffected = repository.DeleteRapportEx(rapportExecution.getId());
        assertEquals(1, rowsAffected);
        assertEquals(Optional.empty(), repository.findRapportExById(rapportExecution.getId()));
    }

}
