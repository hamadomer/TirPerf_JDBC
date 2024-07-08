package org.example.model;

public class FonctionScenario {

    private Integer fonction_id;
    private Integer scenario_id;

    public FonctionScenario(Integer fonction_id, Integer scenario_id) {
        this.fonction_id = fonction_id;
        this.scenario_id = scenario_id;
    }

    public FonctionScenario() {
    }

    public Integer getFonction_id() {
        return fonction_id;
    }

    public void setFonction_id(Integer fonction_id) {
        this.fonction_id = fonction_id;
    }

    public Integer getScenario_id() {
        return scenario_id;
    }

    public void setScenario_id(Integer scenario_id) {
        this.scenario_id = scenario_id;
    }
}
