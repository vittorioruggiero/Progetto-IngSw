package com.example.ratatouille23.entity;

public class Supervisore {

    private String emailSupervisore;
    private String nomeUtenteSupervisore;
    private String passwordSupervisore;

    public Supervisore(String emailSupervisore, String nomeUtenteSupervisore, String passwordSupervisore) {
        this.emailSupervisore = emailSupervisore;
        this.nomeUtenteSupervisore = nomeUtenteSupervisore;
        this.passwordSupervisore = passwordSupervisore;
    }

    public String getEmailSupervisore() {
        return emailSupervisore;
    }

    public void setEmailSupervisore(String emailSupervisore) {
        this.emailSupervisore = emailSupervisore;
    }

    public String getNomeUtenteSupervisore() {
        return nomeUtenteSupervisore;
    }

    public void setNomeUtenteSupervisore(String nomeUtenteSupervisore) {
        this.nomeUtenteSupervisore = nomeUtenteSupervisore;
    }

    public String getPasswordSupervisore() {
        return passwordSupervisore;
    }

    public void setPasswordSupervisore(String passwordSupervisore) {
        this.passwordSupervisore = passwordSupervisore;
    }

}
