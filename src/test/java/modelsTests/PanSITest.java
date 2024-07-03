package modelsTests;

import org.example.model.PanSI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PanSITest {

    PanSI panSI;

    @BeforeEach
    public void setUp() {
        panSI = new PanSI();

        panSI.setId(1);
        panSI.setVersion("0.0.1");
    }

    @Test
    public void testGetPanSIId() {
        Assertions.assertEquals(1, panSI.getId());
    }

    @Test
    public void testGetPanSIVersion() {
        Assertions.assertEquals("0.0.1", panSI.getVersion());
    }
}
