package com.example.ratatouille23server.Entity.SezioneMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SezioneMenuRepository extends JpaRepository<SezioneMenu, String> {
}
