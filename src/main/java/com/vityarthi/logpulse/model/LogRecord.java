package com.vityarthi.logpulse.model;

public class LogRecord {
    private boolean valid;
    private String timestamp;
    private String level;
    private String service;
    private String message;
    private String raw;

    public LogRecord(boolean valid, String raw) {
        this.valid = valid;
        this.raw = raw;
    }

    public LogRecord(boolean valid, String timestamp, String level, String service, String message) {
        this.valid = valid;
        this.timestamp = timestamp;
        this.level = level;
        this.service = service;
        this.message = message;
    }

    public boolean isValid() { return valid; }
    public String getTimestamp() { return timestamp; }
    public String getLevel() { return level; }
    public String getService() { return service; }
    public String getMessage() { return message; }
    public String getRaw() { return raw; }
}
