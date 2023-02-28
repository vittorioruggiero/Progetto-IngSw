package com.example.ratatouille23.entity;

public class Conto {

    private Ordinazione ordinazione;

    public Conto(Ordinazione ordinazione) {
        this.ordinazione = ordinazione;
    }

    public Ordinazione getOrdinazione() {
        return ordinazione;
    }

    public void setOrdinazione(Ordinazione ordinazione) {
        this.ordinazione = ordinazione;
    }
}
