package modelsTests;

import org.example.model.RapportExecution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RapportExecutionTest {

    RapportExecution rapportExecution;

    @BeforeEach
    public void setUp() {
        rapportExecution = new RapportExecution();

        rapportExecution.setId(1);
        rapportExecution.setCallsNumber(100);
        rapportExecution.setSuccessRate(75);
        rapportExecution.setErrors("error 404");
        rapportExecution.setDuration(120);
        rapportExecution.setTirPerfId(1);
    }

    @Test
    public void getRapportExecutionId() {
        Assertions.assertEquals(1, rapportExecution.getId());
    }

    @Test
    public void getRapportExecutionCallsNumber() {
        Assertions.assertEquals(100, rapportExecution.getCallsNumber());
    }

    @Test
    public void getRapportExecutionSuccessRate() {
        Assertions.assertEquals(75, rapportExecution.getSuccessRate());
    }

    @Test
    public void getRapportExecutionErrors() {
        Assertions.assertEquals("error 404", rapportExecution.getErrors());
    }

    @Test
    public void getRapportExecutionDuration() {
        Assertions.assertEquals(120, rapportExecution.getDuration());
    }

    @Test
    public void getRapportExecutionTirPerfId() {
        Assertions.assertEquals(1, rapportExecution.getTirPerfId());
    }
}
