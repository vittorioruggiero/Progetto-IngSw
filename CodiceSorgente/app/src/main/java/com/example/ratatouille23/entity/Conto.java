package com.example.ratatouille23.entity;


public class Conto {

    private int id_conto;
    private java.sql.Date data;
    private Double importo;

    public Conto(int id_conto, java.sql.Date data, Double importo) {
        this.id_conto = id_conto;
        this.data = data;
        this.importo = importo;
    }

    public Conto(java.sql.Date data, Double importo) {
        this.data = data;
        this.importo = importo;
    }

    public int getId_conto() {
        return id_conto;
    }

    public void setId_conto(int id_conto) {
        this.id_conto = id_conto;
    }

    public java.sql.Date getData() {
        return data;
    }

    public void setData(java.sql.Date data) {
        this.data = data;
    }

    public Double getImporto() {
        return importo;
    }

    public void setImporto(Double importo) {
        this.importo = importo;
    }

    @Override
    public String toString() {
        return "Conto{" +
                "id_conto=" + id_conto +
                ", data=" + data +
                ", importo=" + importo +
                '}';
    }
}
