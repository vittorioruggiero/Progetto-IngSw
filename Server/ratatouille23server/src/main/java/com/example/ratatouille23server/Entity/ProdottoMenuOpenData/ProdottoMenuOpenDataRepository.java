package com.example.ratatouille23server.Entity.ProdottoMenuOpenData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoMenuOpenDataRepository extends JpaRepository<ProdottoMenuOpenData, Integer> {

    ProdottoMenuOpenData findByNome(String nome);

}
