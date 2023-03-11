package com.example.ratatouille23.entity;

public class Amministratore {

    private String email;
    private String nomeUtente;
    private String password;
    private String indirizzoAttivita;
    private String nomeAttivita;

    public Amministratore(){

    }

    public Amministratore(String emailAmministratore, String nomeUtenteAmministratore, String passwordAmministratore) {
        this.email = emailAmministratore;
        this.nomeUtente = nomeUtenteAmministratore;
        this.password = passwordAmministratore;
    }

    public String getEmailAmministratore() {
        return email;
    }

    public void setEmailAmministratore(String emailAmministratore) {
        this.email = emailAmministratore;
    }

    public String getNomeUtenteAmministratore() {
        return nomeUtente;
    }

    public void setNomeUtenteAmministratore(String nomeUtenteAmministratore) {
        this.nomeUtente = nomeUtenteAmministratore;
    }

    public String getPasswordAmministratore() {
        return password;
    }

    public void setPasswordAmministratore(String passwordAmministratore) {
        this.password = passwordAmministratore;
    }

    public String getIndirizzoAttivita() {
        return indirizzoAttivita;
    }

    public void setIndirizzoAttivita(String indirizzoAttivita) {
        this.indirizzoAttivita = indirizzoAttivita;
    }

    public String getNomeAttivita() {
        return nomeAttivita;
    }

    public void setNomeAttivita(String nomeAttivita) {
        this.nomeAttivita = nomeAttivita;
    }

    @Override
    public String toString() {
        return "Amministratore{" +
                "email='" + email + '\'' +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", password='" + password + '\'' +
                ", indirizzoAttivita='" + indirizzoAttivita + '\'' +
                ", nomeAttivita='" + nomeAttivita + '\'' +
                '}';
    }
}
