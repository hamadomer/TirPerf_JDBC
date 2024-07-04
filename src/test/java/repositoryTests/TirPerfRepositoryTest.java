package repositoryTests;

import org.example.model.TirPerf;
import org.example.repository.TirPerfRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TirPerfRepositoryTest {

    private TirPerfRepository repository;
    private TirPerf tirPerf;



    @BeforeEach
    public void setUp() throws SQLException {
        repository = TirPerfRepository.getInstance();
        tirPerf = new TirPerf(new java.sql.Date(2024, 4, 12), 1);
        Optional<Integer> generatedId = repository.createTirPerf(tirPerf);
        tirPerf.setId(generatedId.orElseThrow(() -> new RuntimeException("Could not create TirPerf")));
    }

    @AfterEach
    public void tearDown() throws SQLException {
        repository.connection.prepareStatement("DELETE FROM tirperf").execute();
    }

    @Test
    public void testCreateTirPerf() throws SQLException {
        Optional<TirPerf> createdTirPerf = repository.findTirPerfById(tirPerf.getId());
        assertTrue(createdTirPerf.isPresent());
        assertEquals(tirPerf.getId(), createdTirPerf.get().getId());
        assertEquals(tirPerf.getDate().toLocalDate(), createdTirPerf.get().getDate().toLocalDate());
        assertEquals(tirPerf.getScenarioId(), createdTirPerf.get().getScenarioId());
    }
}
