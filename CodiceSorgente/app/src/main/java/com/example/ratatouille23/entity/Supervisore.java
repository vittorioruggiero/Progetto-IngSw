package com.example.ratatouille23.entity;

public class Supervisore {

    private String email;
    private String nomeUtente;
    private String password;
    private int idAttivita;
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

    public int getIdAttivita() {
        return idAttivita;
    }

    public void setIdAttivita(int idAttivita) {
        this.idAttivita = idAttivita;
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
                ", idAttivita='" + idAttivita + '\'' +
                ", primoAccesso=" + primoAccesso +
                '}';
    }
}
