package com.fanus.coverage;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

public class WirelessCoverageAnalyserTest {

    private WirelessCoverageAnalyser analyser = new WirelessCoverageAnalyser();

    @Test
    public void testSignalStrengthCalculation() {
        double strength = analyser.calculateSignalStrength(10, 2400);
        // 10m la -70 around varanum
        assertTrue("Signal should be around -70", strength < -60 && strength > -80);
    }

    @Test
    public void testCoverageStatusExcellent() {
        // Direct-a excellent range test pannrom
        String status = analyser.getCoverageStatus(-40);
        assertEquals("Excellent Coverage", status);
    }

    @Test
    public void testCoverageStatusGood() {
        String status = analyser.getCoverageStatus(-60);
        assertEquals("Good Coverage", status);
    }

    @Test
    public void testCoverageStatusPoor() {
        String status = analyser.getCoverageStatus(-95);
        assertEquals("Poor Coverage / No Coverage", status);
    }

    @Test
    public void testAnalyzeCoverageMap() {
        Map<String, Object> result = analyser.analyzeCoverage(50, 2400);
        assertNotNull(result);
        assertTrue(result.containsKey("signalStrength_dBm"));
        assertTrue(result.containsKey("status"));
        assertEquals(50.0, (Double) result.get("distance_m"), 0.01);
    }
}