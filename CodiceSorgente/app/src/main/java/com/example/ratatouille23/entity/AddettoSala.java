package com.example.ratatouille23.entity;

public class AddettoSala {

    private String email;
    private String nomeUtente;
    private String password;

    public AddettoSala(String emailAddettoSala, String nomeUtenteAddettoSala, String passwordAddettoSala) {
        this.email = emailAddettoSala;
        this.nomeUtente = nomeUtenteAddettoSala;
        this.password = passwordAddettoSala;
    }

    public String getEmailAddettoSala() {
        return email;
    }

    public void setEmailAddettoSala(String emailAddettoSala) {
        this.email = emailAddettoSala;
    }

    public String getNomeUtenteAddettoSala() {
        return nomeUtente;
    }

    public void setNomeUtenteAddettoSala(String nomeUtenteAddettoSala) {
        this.nomeUtente = nomeUtenteAddettoSala;
    }

    public String getPasswordAddettoSala() {
        return password;
    }

    public void setPasswordAddettoSala(String passwordAddettoSala) {
        this.password = passwordAddettoSala;
    }
}
