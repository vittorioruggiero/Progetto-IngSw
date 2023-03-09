package com.example.ratatouille23.entity;

public class Avviso {

    private int id;
    private String avviso;

    public Avviso(String avviso) {
        this.avviso = avviso;
    }

    public String getAvviso() {
        return avviso;
    }

    public void setAvviso(String avviso) {
        this.avviso = avviso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
