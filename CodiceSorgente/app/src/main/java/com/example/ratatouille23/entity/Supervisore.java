package com.example.ratatouille23.entity;

public class Supervisore {

    private String email;
    private String nomeUtente;
    private String password;
    private String nomeAttivita;
    private String indirizzoAttivita;
    private Boolean primoAccesso;

    public Supervisore(){

    }

    public Supervisore(String emailSupervisore, String nomeUtenteSupervisore, String passwordSupervisore) {
        this.email = emailSupervisore;
        this.nomeUtente = nomeUtenteSupervisore;
        this.password = passwordSupervisore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean getPrimoAccesso() {
        return primoAccesso;
    }

    public void setPrimoAccesso(Boolean primoAccesso) {
        this.primoAccesso = primoAccesso;
    }

    @Override
    public String toString() {
        return "Supervisore{" +
                "email='" + email + '\'' +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", password='" + password + '\'' +
                ", nomeAttivita='" + nomeAttivita + '\'' +
                ", indirizzoAttivita='" + indirizzoAttivita + '\'' +
                ", primoAccesso=" + primoAccesso +
                '}';
    }
}
