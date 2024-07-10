package repositoryTests;


import org.example.Helpers.FlywayExtension;
import org.example.model.Fonction;
import org.example.repository.FonctionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(FlywayExtension.class)
public class FonctionRepositoryTest {
    private FonctionRepository repository;
    private Fonction fonction;

    @BeforeEach
    public void setUp() throws SQLException {
        repository = FonctionRepository.getInstance();
        fonction = new Fonction("test fonction");
        Optional<Integer> generatedId = repository.createFonction(fonction);
        fonction.setId(generatedId.orElseThrow(()-> new RuntimeException("Fonction could not be created")));
    }

    @Test
    public void testCreateFonction() throws SQLException {
        Optional<Fonction> createdFonction = repository.findFonctionById(fonction.getId());
        assertTrue(createdFonction.isPresent());
        assertEquals("test fonction", createdFonction.get().getName());
    }

    @Test
    public void testUpdateFonction() throws SQLException {
        int rowsAffected = repository.UpdateFonction(fonction.getId(), "UpdateFonction");
        assertEquals(1, rowsAffected);
        Fonction updatedFonction = repository.findFonctionById(fonction.getId()).orElseThrow(()-> new RuntimeException("Fonction could not be found"));
        assertEquals("UpdateFonction", updatedFonction.getName());
    }

    @Test
    public void testDeleteFonction() throws SQLException {
        int deletedRows = repository.deleteFonction(fonction.getId());
        assertEquals(1, deletedRows);
        assertEquals(Optional.empty(), repository.findFonctionById(fonction.getId()));
    }
}
