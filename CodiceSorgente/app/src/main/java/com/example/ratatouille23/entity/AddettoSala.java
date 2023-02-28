package com.example.ratatouille23.entity;

public class AddettoSala {

    private String emailAddettoSala;
    private String nomeUtenteAddettoSala;
    private String passwordAddettoSala;

    public AddettoSala(String emailAddettoSala, String nomeUtenteAddettoSala, String passwordAddettoSala) {
        this.emailAddettoSala = emailAddettoSala;
        this.nomeUtenteAddettoSala = nomeUtenteAddettoSala;
        this.passwordAddettoSala = passwordAddettoSala;
    }

    public String getEmailAddettoSala() {
        return emailAddettoSala;
    }

    public void setEmailAddettoSala(String emailAddettoSala) {
        this.emailAddettoSala = emailAddettoSala;
    }

    public String getNomeUtenteAddettoSala() {
        return nomeUtenteAddettoSala;
    }

    public void setNomeUtenteAddettoSala(String nomeUtenteAddettoSala) {
        this.nomeUtenteAddettoSala = nomeUtenteAddettoSala;
    }

    public String getPasswordAddettoSala() {
        return passwordAddettoSala;
    }

    public void setPasswordAddettoSala(String passwordAddettoSala) {
        this.passwordAddettoSala = passwordAddettoSala;
    }
}
