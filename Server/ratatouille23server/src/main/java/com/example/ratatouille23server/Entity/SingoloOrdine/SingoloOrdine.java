package com.example.ratatouille23server.Entity.SingoloOrdine;

import com.example.ratatouille23server.Entity.Attivita.Attivita;
import com.example.ratatouille23server.Entity.Ordinazione.Ordinazione;
import jakarta.persistence.*;
@Entity
@Table(name = "singoloordine")
public class SingoloOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_singolo_ordine;
    private int quantita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_singolo_ordine", referencedColumnName = "id_ordinazione", foreignKey = @ForeignKey(name = "attivita_fkey"))
    private Ordinazione ordinazione;





}
