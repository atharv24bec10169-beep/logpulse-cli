package com.vityarthi.logpulse.detector;

import com.vityarthi.logpulse.model.LogRecord;
import java.util.List;

public class AnomalyDetector {
    private final double threshold;
    private static final List<String> HIGH_RISK_KEYWORDS = List.of(
        "timeout", "out of memory", "terminated", "failed", "denied", "exception", "refused"
    );

    public AnomalyDetector(double threshold) {
        this.threshold = threshold;
    }

    public double calculateScore(LogRecord record) {
        double score = 0.0;
        switch (record.getLevel().toUpperCase()) {
            case "CRITICAL" -> score += 3.0;
            case "ERROR"    -> score += 2.0;
            case "WARN"     -> score += 1.0;
            default         -> score += 0.0;
        }

        String msgLower = record.getMessage().toLowerCase();
        for (String keyword : HIGH_RISK_KEYWORDS) {
            if (msgLower.contains(keyword)) {
                score += 1.5;
                break;
            }
        }
        return score;
    }

    public boolean isAnomaly(LogRecord record) {
        return calculateScore(record) >= threshold;
    }
}
