package com.vityarthi.logpulse.parser;

import com.vityarthi.logpulse.model.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^\\[(?<timestamp>[^\\]]+)\\] \\[(?<level>INFO|WARN|ERROR|CRITICAL)\\] \\[(?<service>[^\\]]+)\\] (?<message>.*)$"
    );

    public LogRecord parseLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return new LogRecord(false, line);
        }
        Matcher matcher = LOG_PATTERN.matcher(line.trim());
        if (matcher.matches()) {
            return new LogRecord(
                true,
                matcher.group("timestamp"),
                matcher.group("level"),
                matcher.group("service"),
                matcher.group("message")
            );
        }
        return new LogRecord(false, line);
    }
}
