package com.example.ratatouille23server.Entity.ProdottoMenu;

import com.example.ratatouille23server.Entity.SezioneMenu.SezioneMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoMenuRepository extends CrudRepository<ProdottoMenu, String> {
}
