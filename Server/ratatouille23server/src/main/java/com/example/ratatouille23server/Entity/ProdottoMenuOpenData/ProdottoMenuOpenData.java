package com.example.ratatouille23server.Entity.ProdottoMenuOpenData;

import jakarta.persistence.*;

@Entity
@Table(name = "prodottoopenfoodfacts", uniqueConstraints = @UniqueConstraint(name = "unique_nome_prodotto", columnNames = "nome"))
public class ProdottoMenuOpenData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "allergeni")
    private String allergeni;

    @Column(name = "ingredienti")
    private String ingredienti;

    @Column(name = "nome")
    private String nome;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getAllergeni() {
        return allergeni;
    }

    public void setAllergeni(String allergeni) {
        this.allergeni = allergeni;
    }

    @Override
    public String toString() {
        return "ProdottoMenuOpenData{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ingredienti='" + ingredienti + '\'' +
                ", allergeni='" + allergeni + '\'' +
                '}';
    }
}
