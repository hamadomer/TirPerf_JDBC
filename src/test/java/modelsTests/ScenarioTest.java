package modelsTests;

import org.example.model.Scenario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScenarioTest {

    private Scenario scenario;

    @BeforeEach
    public void setUp () {

        scenario = new Scenario();

        scenario.setId(1);
        scenario.setDescription("Use 16gb and 100 users");
        scenario.setApplicatif_id(1);
        scenario.setScenario_fonctions("Mail treatment");

    }

    @Test
    public void testGetScenarioId () {
        Assertions.assertEquals(1, scenario.getId());
    }

    @Test
    public void testGetScenarioDescription() {
        Assertions.assertEquals("Use 16gb and 100 users", scenario.getDescription());
    }

    @Test
    public void testGetScenarioApplicatifId() {
        Assertions.assertEquals(1, scenario.getApplicatif_id());
    }

    @Test
    public void testGetScenarioFonctions() {
        Assertions.assertEquals("Mail treatment", scenario.getScenario_fonctions());
    }
}
