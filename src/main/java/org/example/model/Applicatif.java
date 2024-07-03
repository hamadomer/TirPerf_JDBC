package org.example.model;


public class Applicatif {

    private Integer id;
    private String intitule;
    private String version;
    private String fonctions;

    public Applicatif(Integer id, String version, String intitule, String fonctions) {
        this.id = id;
        this.version = version;
        this.intitule = intitule;
        this.fonctions = fonctions;
    }

    public Applicatif() {

    }

    public Applicatif (String intitule, String version, String fonctions) {
        this.intitule = intitule;
        this.version  = version;
        this.fonctions = fonctions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getFonctions() {
        return fonctions;
    }

    public void setFonctions(String fonctions) {
       this.fonctions = fonctions;
    }
}
