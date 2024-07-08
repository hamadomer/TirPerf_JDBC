package modelsTests;

import org.example.DB.DbUtil;
import org.example.model.Applicatif;
import org.example.model.Fonction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class ApplicatifTest {

    private Applicatif app;



    @BeforeEach
    public void setUp() {
        // init the application model
        app = new Applicatif();


        // use the setters
        app.setId(1);
        app.setIntitule("AgentMail");
        app.setVersion("0.0.1");
        app.setFonction(1);
    }

    @Test
    public void testGetApplicatifIntitule() {
        Assertions.assertEquals("AgentMail", app.getIntitule());
    }

    @Test
    public void testGetApplicatifVersion() {
        Assertions.assertEquals("0.0.1", app.getVersion());
    }

//    @Test
//    public void testGetApplicatifFonctionsIsNotEmpty() {
//        Assertions.assertTrue(!app.getFonction().getId());
//    }

//    @Test
//    public void testGetApplicatifFonctions() {
//        Set<Fonction> fonctions = app.getFonctions();
//        Assertions.assertTrue(fonctions.contains(new Fonction("Mail treatment")));
//    }
}