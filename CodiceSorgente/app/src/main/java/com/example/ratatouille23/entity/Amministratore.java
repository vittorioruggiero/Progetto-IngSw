package com.example.ratatouille23.entity;

public class Amministratore {

    private String email;
    private String nomeUtente;
    private String password;
    private int idAttivita;

    public Amministratore(){

    }

    public Amministratore(String emailAmministratore, String nomeUtenteAmministratore, String passwordAmministratore) {
        this.email = emailAmministratore;
        this.nomeUtente = nomeUtenteAmministratore;
        this.password = passwordAmministratore;
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

    @Override
    public String toString() {
        return "Amministratore{" +
                "email='" + email + '\'' +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", password='" + password + '\'' +
                ", idAttivita='" + idAttivita + '\'' +
                '}';
    }
}
