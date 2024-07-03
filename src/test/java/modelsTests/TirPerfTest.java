package modelsTests;

import org.example.model.TirPerf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TirPerfTest {

    TirPerf tirPerf;
    SimpleDateFormat dateFormat;

    @BeforeEach
    public void setUp() {
        tirPerf = new TirPerf();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            java.util.Date utilDate = dateFormat.parse("24/04/2024");
            Date sqlDate = new Date(utilDate.getTime());

            tirPerf.setId(1);
            tirPerf.setDate(sqlDate);
            tirPerf.setScenarioId(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTirPerfId() {
        Assertions.assertEquals(1, tirPerf.getId());
    }

    @Test
    public void testGetTirPerfDate() {
        try {
            java.util.Date expectedUtilDate = dateFormat.parse("24/04/2024");
            Date expectedDate = new Date(expectedUtilDate.getTime());
            Assertions.assertEquals(expectedDate, tirPerf.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTirPerfScenarioId() {
        Assertions.assertEquals(1, tirPerf.getScenarioId());
    }
}
