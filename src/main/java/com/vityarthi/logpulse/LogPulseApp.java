package com.vityarthi.logpulse;

import com.vityarthi.logpulse.model.LogRecord;
import com.vityarthi.logpulse.parser.LogParser;
import com.vityarthi.logpulse.detector.AnomalyDetector;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LogPulseApp {
    public static void main(String[] args) {
        String filePath = "sample_data/app_logs.log";
        double threshold = 2.0;

        for (int i = 0; i < args.length; i++) {
            if ("--file".equals(args[i]) && i + 1 < args.length) filePath = args[++i];
            if ("--threshold".equals(args[i]) && i + 1 < args.length) threshold = Double.parseDouble(args[++i]);
        }

        System.out.println("============================================================");
        System.out.println(" LOGPULSE ANALYTICS & ANOMALY REPORT (JAVA EDITION)");
        System.out.println(" Author: Atharv Patil | Reg No: 24BEC10169");
        System.out.println("============================================================");

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            LogParser parser = new LogParser();
            AnomalyDetector detector = new AnomalyDetector(threshold);

            Map<String, Integer> levelCounts = new HashMap<>();
            Map<String, Integer> serviceCounts = new HashMap<>();
            List<String> anomalyAlerts = new ArrayList<>();

            int validCount = 0;
            int invalidCount = 0;

            for (String line : lines) {
                LogRecord record = parser.parseLine(line);
                if (record.isValid()) {
                    validCount++;
                    levelCounts.put(record.getLevel(), levelCounts.getOrDefault(record.getLevel(), 0) + 1);
                    serviceCounts.put(record.getService(), serviceCounts.getOrDefault(record.getService(), 0) + 1);

                    if (detector.isAnomaly(record)) {
                        double score = detector.calculateScore(record);
                        anomalyAlerts.add(String.format(
                            "[%s] [%s] %s | Score: %.1f | %s",
                            record.getTimestamp(), record.getLevel(), record.getService(), score, record.getMessage()
                        ));
                    }
                } else {
                    invalidCount++;
                }
            }

            System.out.println("Total Lines Processed : " + lines.size());
            System.out.println("Valid Records         : " + validCount);
            System.out.println("Invalid Records       : " + invalidCount);
            System.out.println("\nSeverity Counts       : " + levelCounts);
            System.out.println("Service Activity      : " + serviceCounts);

            System.out.println("\n------------------------------------------------------------");
            System.out.println("ANOMALY ALERTS (Threshold >= " + threshold + ")");
            System.out.println("------------------------------------------------------------");
            if (anomalyAlerts.isEmpty()) {
                System.out.println("No anomalies detected.");
            } else {
                anomalyAlerts.forEach(System.out::println);
            }

        } catch (Exception e) {
            System.err.println("[ERROR] Failed to execute LogPulse pipeline: " + e.getMessage());
        }
    }
}
