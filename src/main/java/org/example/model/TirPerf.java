package org.example.model;

import java.sql.Date;
import java.util.Objects;


public class TirPerf {

    private Integer id;
    private Date date;
    private Integer scenarioId;

    public TirPerf(Integer id, Date date, Integer scenarioId) {
        this.id = id;
        this.date = date;
        this.scenarioId = scenarioId;
    }

    public TirPerf() {}

    public TirPerf(Date date, Integer scenarioId) {
        this.date = new Date(System.currentTimeMillis());
        this.scenarioId = scenarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TirPerf tirPerf = (TirPerf) o;
        return Objects.equals(id, tirPerf.id) && Objects.equals(date, tirPerf.date) && Objects.equals(scenarioId, tirPerf.scenarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, scenarioId);
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
