package com.example.ratatouille23.entity;

public class Avviso {

    private int id;
    private String avviso;
    private String nomeAttivita;
    private String indirizzoAttivita;
    private String email;

    public Avviso(){

    }

    public Avviso(String avviso, String nomeAttivita, String indirizzoAttivita){
        this.avviso = avviso;
        this.nomeAttivita = nomeAttivita;
        this.indirizzoAttivita = indirizzoAttivita;
    }

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

    public String getNomeAttivita() {
        return nomeAttivita;
    }

    public void setNomeAttivita(String nomeAttivita) {
        this.nomeAttivita = nomeAttivita;
    }

    public String getIndirizzoAttivita() {
        return indirizzoAttivita;
    }

    public void setIndirizzoAttivita(String indirizzoAttivita) {
        this.indirizzoAttivita = indirizzoAttivita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Avviso{" +
                "id=" + id +
                ", avviso='" + avviso + '\'' +
                ", nomeAttivita='" + nomeAttivita + '\'' +
                ", indirizzoAttivita='" + indirizzoAttivita + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
