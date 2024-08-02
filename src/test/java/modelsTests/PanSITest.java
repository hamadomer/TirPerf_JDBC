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

        panSI.setId(16);
    }

    @Test
    public void testGetPanSIId() {
        Assertions.assertEquals(16, panSI.getId());
    }
}
