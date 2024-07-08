package org.example.model;

import java.util.HashSet;
import java.util.Set;

public class Scenario {

    private Integer id;

    private String description;

    private Integer applicatif_id;


    public Scenario(Integer id, String description, Integer applicatif_id) {
        this.id = id;
        this.description = description;
        this.applicatif_id = applicatif_id;
    }

    public Scenario (String description, Integer applicatif_id) {
        this.description = description;
        this.applicatif_id = applicatif_id;
    }

    public Scenario () {}



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getApplicatif_id() {
        return applicatif_id;
    }

    public void setApplicatif_id(Integer applicatif_id) {
        this.applicatif_id = applicatif_id;
    }

}
