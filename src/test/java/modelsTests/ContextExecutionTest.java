package modelsTests;

import org.example.model.ContextExecution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContextExecutionTest {

    ContextExecution contextExecution;

    @BeforeEach
    public void setUp() {
        contextExecution = new ContextExecution();

        contextExecution.setId(1);
        contextExecution.setPanSiId(1);
        contextExecution.setEnv("Apache Tomcat");
        contextExecution.setInfoComplementaire("tested with 100 users");
        contextExecution.setTirPerfId(1);
    }

    @Test
    public void testGetContextExecutionId() {
        Assertions.assertEquals(1, contextExecution.getId());
    }

    @Test
    public void testGetContextExecutionPanSiId() {
        Assertions.assertEquals(1, contextExecution.getId());
    }

    @Test
    public void testGetContextExecutionEnv() {
        Assertions.assertEquals("Apache Tomcat", contextExecution.getEnv());
    }

    @Test
    public void testGetContextExecutionInfoComolementaire() {
        Assertions.assertEquals("tested with 100 users", contextExecution.getInfoComplementaire());
    }

    @Test
    public void testGetContextExecutionTirPerfId() {
        Assertions.assertEquals(1, contextExecution.getTirPerfId());
    }

}
