package com.example.ratatouille23.entity;

import com.example.ratatouille23.entity.ProdottoMenu;

public class SingoloOrdine {

    private int id;
    private ProdottoMenu prodottoMenu;
    private int quantitaProdotto;

    public SingoloOrdine(ProdottoMenu prodottoMenu, int quantitaProdotto) {
        this.prodottoMenu = prodottoMenu;
        this.quantitaProdotto = quantitaProdotto;
    }

    public ProdottoMenu getProdottoMenu() {
        return prodottoMenu;
    }

    public void setProdottoMenu(ProdottoMenu prodottoMenu) {
        this.prodottoMenu = prodottoMenu;
    }

    public int getQuantitaProdotto() {
        return quantitaProdotto;
    }

    public void setQuantitaProdotto(int quantitaProdotto) {
        this.quantitaProdotto = quantitaProdotto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double calcolaTotale() {
        return this.prodottoMenu.getPrezzo() * this.quantitaProdotto;
    }
}
