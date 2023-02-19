package com.example.ratatouille23;

import java.util.ArrayList;
import java.util.List;

public class SezioneMenu {

    private String titolo;
    private List<ProdottoMenu> prodottiMenu;

    public SezioneMenu(String titolo, List<ProdottoMenu> prodottiMenu) {
        this.titolo = titolo;
        this.prodottiMenu = prodottiMenu;
    }

    public SezioneMenu(String titolo) {
        this.titolo = titolo;
    }

    public SezioneMenu() {
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public List<ProdottoMenu> getProdottiMenu() {
        return prodottiMenu;
    }

    public void setProdottiMenu(List<ProdottoMenu> prodottiMenu) {
        this.prodottiMenu = prodottiMenu;
    }

    public void addItems(ArrayList<ProdottoMenu> items) {
        this.prodottiMenu.addAll(items);
    }

    public void addItem(ProdottoMenu item) {
        prodottiMenu.add(item);
    }

}
