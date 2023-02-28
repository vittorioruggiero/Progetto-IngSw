package com.example.ratatouille23.entity;

public class Amministratore {

    private String emailAmministratore;
    private String nomeUtenteAmministratore;
    private String passwordAmministratore;

    public Amministratore(String emailAmministratore, String nomeUtenteAmministratore, String passwordAmministratore) {
        this.emailAmministratore = emailAmministratore;
        this.nomeUtenteAmministratore = nomeUtenteAmministratore;
        this.passwordAmministratore = passwordAmministratore;
    }

    public String getEmailAmministratore() {
        return emailAmministratore;
    }

    public void setEmailAmministratore(String emailAmministratore) {
        this.emailAmministratore = emailAmministratore;
    }

    public String getNomeUtenteAmministratore() {
        return nomeUtenteAmministratore;
    }

    public void setNomeUtenteAmministratore(String nomeUtenteAmministratore) {
        this.nomeUtenteAmministratore = nomeUtenteAmministratore;
    }

    public String getPasswordAmministratore() {
        return passwordAmministratore;
    }

    public void setPasswordAmministratore(String passwordAmministratore) {
        this.passwordAmministratore = passwordAmministratore;
    }
}
