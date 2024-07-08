package org.example.model;

import org.example.model.Fonction;

public class Applicatif {
    private Integer id;
    private String version;
    private String intitule;
    private Integer fonction;

    public Applicatif(String version, String intitule, Integer fonction) {
        this.version = version;
        this.intitule = intitule;
        this.fonction = fonction;
    }

    public Applicatif () {}

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

    public Integer getFonction() {
        return fonction;
    }

    public void setFonction(Integer fonction) {
        this.fonction = fonction;
    }
}