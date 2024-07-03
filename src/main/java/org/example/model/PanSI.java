package org.example.model;

public class PanSI {
    private Integer id;
    private String version;

    public PanSI(Integer id, String version) {
        this.id = id;
        this.version = version;
    }

    public PanSI() {}

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
}
