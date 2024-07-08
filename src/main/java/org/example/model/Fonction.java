package org.example.model;

public class Fonction {
    private int id;
    private String name;

    public Fonction() {}

    public Fonction(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Fonction(int id) {
        this.id = id;
    }

    public Fonction (String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}