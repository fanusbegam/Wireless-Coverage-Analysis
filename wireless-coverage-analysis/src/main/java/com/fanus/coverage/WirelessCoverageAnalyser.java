package com.fanus.coverage;

import java.util.*;

public class WirelessCoverageAnalyser {

    // Signal strength calculation
    public double calculateSignalStrength(double distance, double frequency) {
        // Simple Free Space Path Loss model
        double pathLoss = 20 * Math.log10(distance) + 20 * Math.log10(frequency) + 32.44;
        double signalStrength = -pathLoss + 50; // Assume tx power 50
        return signalStrength;
    }

    public String getCoverageStatus(double signalStrength) {
        if (signalStrength >= -50) {
            return "Excellent Coverage";
        } else if (signalStrength >= -70) {
            return "Good Coverage";
        } else if (signalStrength >= -85) {
            return "Fair Coverage";
        } else {
            return "Poor Coverage / No Coverage";
        }
    }

    public Map<String, Object> analyzeCoverage(double distance, double frequency) {
        Map<String, Object> result = new HashMap<>();
        double strength = calculateSignalStrength(distance, frequency);
        String status = getCoverageStatus(strength);
        
        result.put("distance_m", distance);
        result.put("frequency_MHz", frequency);
        result.put("signalStrength_dBm", strength);
        result.put("status", status);
        
        return result;
    }

    public static void main(String[] args) {
        WirelessCoverageAnalyser analyser = new WirelessCoverageAnalyser();
        
        System.out.println("=== Wireless Coverage Analysis ===");
        
        double[] distances = {10, 50, 100, 500};
        double frequency = 2400; // 2.4 GHz WiFi
        
        for (double dist : distances) {
            Map<String, Object> data = analyser.analyzeCoverage(dist, frequency);
            System.out.println("Distance: " + dist + "m -> Strength: " + 
                String.format("%.2f", data.get("signalStrength_dBm")) + " dBm -> " + 
                data.get("status"));
        }
    }
}