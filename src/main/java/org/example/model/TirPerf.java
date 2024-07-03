package org.example.model;

import java.sql.Date;


public class TirPerf {

    private Integer id;
    private Date date;
    private Integer scenarioId;

    public TirPerf(Date date, Integer id, Integer scenarioId) {
        this.date = date;
        this.id = id;
        this.scenarioId = scenarioId;
    }

    public TirPerf() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(Integer scenarioId) {
        this.scenarioId = scenarioId;
    }
}
