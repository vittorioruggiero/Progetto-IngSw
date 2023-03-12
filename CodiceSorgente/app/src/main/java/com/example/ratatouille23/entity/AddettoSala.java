package com.example.ratatouille23.entity;

public class AddettoSala {

    private String email;
    private String nomeUtente;
    private String password;
    private String nomeAttivita;
    private String indirizzoAttivita;

    public AddettoSala(){

    }

    public AddettoSala(String emailAddettoSala, String nomeUtenteAddettoSala, String passwordAddettoSala) {
        this.email = emailAddettoSala;
        this.nomeUtente = nomeUtenteAddettoSala;
        this.password = passwordAddettoSala;
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

    @Override
    public String toString() {
        return "AddettoSala{" +
                "email='" + email + '\'' +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", password='" + password + '\'' +
                ", nomeAttivita='" + nomeAttivita + '\'' +
                ", indirizzoAttivita='" + indirizzoAttivita + '\'' +
                '}';
    }
}
