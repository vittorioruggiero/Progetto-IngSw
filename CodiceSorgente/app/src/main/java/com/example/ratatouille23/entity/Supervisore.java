package com.example.ratatouille23.entity;

public class Supervisore {

    private String email;
    private String nomeUtente;
    private String password;

    public Supervisore(){

    }

    public Supervisore(String emailSupervisore, String nomeUtenteSupervisore, String passwordSupervisore) {
        this.email = emailSupervisore;
        this.nomeUtente = nomeUtenteSupervisore;
        this.password = passwordSupervisore;
    }

    public String getEmailSupervisore() {
        return email;
    }

    public void setEmailSupervisore(String emailSupervisore) {
        this.email = emailSupervisore;
    }

    public String getNomeUtenteSupervisore() {
        return nomeUtente;
    }

    public void setNomeUtenteSupervisore(String nomeUtenteSupervisore) {
        this.nomeUtente = nomeUtenteSupervisore;
    }

    public String getPasswordSupervisore() {
        return password;
    }

    public void setPasswordSupervisore(String passwordSupervisore) {
        this.password = passwordSupervisore;
    }

}
