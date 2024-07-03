package org.example.model;

public class ContextExecution {
    private Integer id;
    private Integer panSiId;
    private String env;
    private String infoComplementaire;
    private Integer tirPerfId;

    public ContextExecution(Integer id, Integer panSiId, String env, String infoComplementaire, Integer tirPerfId) {
        this.id = id;
        this.panSiId = panSiId;
        this.env = env;
        this.infoComplementaire = infoComplementaire;
        this.tirPerfId = tirPerfId;
    }

    public ContextExecution() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPanSiId() {
        return panSiId;
    }

    public void setPanSiId(Integer panSiId) {
        this.panSiId = panSiId;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getInfoComplementaire() {
        return infoComplementaire;
    }

    public void setInfoComplementaire(String infoComplementaire) {
        this.infoComplementaire = infoComplementaire;
    }

    public Integer getTirPerfId() {
        return tirPerfId;
    }

    public void setTirPerfId(Integer tirPerfId) {
        this.tirPerfId = tirPerfId;
    }
}
