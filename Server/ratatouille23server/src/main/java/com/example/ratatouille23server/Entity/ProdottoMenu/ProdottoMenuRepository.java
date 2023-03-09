package com.example.ratatouille23server.Entity.ProdottoMenu;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoMenuRepository extends CrudRepository<ProdottoMenu, String> {
}
