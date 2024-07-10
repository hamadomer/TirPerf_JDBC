package repositoryTests;

import org.example.Helpers.FlywayExtension;
import org.example.model.TirPerf;
import org.example.repository.TirPerfRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(FlywayExtension.class)
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

    @Test
    public void testCreateTirPerf() throws SQLException {
        Optional<TirPerf> createdTirPerf = repository.findTirPerfById(tirPerf.getId());
        assertTrue(createdTirPerf.isPresent());
        assertEquals(tirPerf.getId(), createdTirPerf.get().getId());
        assertEquals(tirPerf.getDate().toLocalDate(), createdTirPerf.get().getDate().toLocalDate());
        assertEquals(tirPerf.getScenarioId(), createdTirPerf.get().getScenarioId());
    }

    @Test
    public void testUpdateTirPerf() throws SQLException {
        tirPerf.setDate(new Date(2012,12,04));
        tirPerf.setScenarioId(3);
        int rowsAffected = repository.UpdateTirPerf(tirPerf);
        assertEquals(1, rowsAffected);
        TirPerf updatedTirPerf = repository.findTirPerfById(tirPerf.getId()).orElseThrow(()-> new RuntimeException("Could not find TirPerf"));
        assertEquals(tirPerf.getId(), updatedTirPerf.getId());
        assertEquals(tirPerf.getDate().toLocalDate(), updatedTirPerf.getDate().toLocalDate());
        assertEquals(3, updatedTirPerf.getScenarioId());
    }

    @Test
    public void testDeleteTirPerf() throws SQLException {
        int rowsAffected = repository.DeleteTirPerf(tirPerf.getId());
        assertEquals(1, rowsAffected);
        assertEquals(Optional.empty(), repository.findTirPerfById(tirPerf.getId()));
    }
}
