package com.example.ratatouille23server.Entity.Ordinazione;

import com.example.ratatouille23server.Entity.ProdottoMenu.ProdottoMenu;
import com.example.ratatouille23server.Entity.SingoloOrdine.SingoloOrdine;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ordinazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ordinazione;
    private int numeroTavolo;
    private int numeroCommensali;


}
