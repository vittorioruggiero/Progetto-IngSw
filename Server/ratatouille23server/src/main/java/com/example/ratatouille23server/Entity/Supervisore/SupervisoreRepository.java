package com.example.ratatouille23server.Entity.Supervisore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisoreRepository extends JpaRepository<Supervisore, String> {
}
