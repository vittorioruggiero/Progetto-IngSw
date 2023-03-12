package com.example.ratatouille23.entity;

import com.example.ratatouille23.entity.ProdottoMenu;

import java.util.ArrayList;
import java.util.List;

public class SezioneMenu {

    private String nome;
    private String nomeAttivita;
    private String indirizzoAttivita;
    private List<ProdottoMenu> prodottiMenu;

    public SezioneMenu(String nome, List<ProdottoMenu> prodottiMenu) {
        this.nome = nome;
        this.prodottiMenu = prodottiMenu;
    }

    public SezioneMenu(String nome) {
        this.nome = nome;
    }

    public SezioneMenu() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void addItem(ProdottoMenu prodottoMenu) {
        prodottiMenu.add(prodottoMenu);
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
        return "SezioneMenu{" +
                "nome='" + nome + '\'' +
                ", nomeAttivita='" + nomeAttivita + '\'' +
                ", indirizzoAttivita='" + indirizzoAttivita + '\'' +
                '}';
    }
}
