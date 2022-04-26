package ru.igar15.skillsaggregator.service;

public class EmptySkillReportException extends RuntimeException {
    public EmptySkillReportException(String message) {
        super(message);
    }
}