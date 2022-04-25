package ru.igar15.skillsaggregator.util;

public class EmptySkillReportException extends RuntimeException {
    public EmptySkillReportException(String message) {
        super(message);
    }
}