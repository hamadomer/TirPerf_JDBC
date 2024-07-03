package org.example.model;

public class RapportExecution {
    private Integer id;
    private Integer callsNumber;
    private Integer successRate;
    private String errors;
    private Integer duration;
    private Integer tirPerfId;

    public RapportExecution(Integer id, Integer callsNumber, Integer successRate, String errors, Integer duration, Integer tirPerfId) {
        this.id = id;
        this.callsNumber = callsNumber;
        this.successRate = successRate;
        this.errors = errors;
        this.duration = duration;
        this.tirPerfId = tirPerfId;
    }

    public RapportExecution() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCallsNumber() {
        return callsNumber;
    }

    public void setCallsNumber(Integer callsNumber) {
        this.callsNumber = callsNumber;
    }

    public Integer getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Integer successRate) {
        this.successRate = successRate;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public Integer getTirPerfId() {
        return tirPerfId;
    }

    public void setTirPerfId(Integer tirPerfId) {
        this.tirPerfId = tirPerfId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
