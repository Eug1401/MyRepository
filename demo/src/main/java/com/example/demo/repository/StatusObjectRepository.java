package com.example.demo.repository;

import com.example.demo.Entity.StatusObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusObjectRepository extends JpaRepository<StatusObject, String> {

    //La query prende gli oggetti il cui nome inizia per consonante
    //Estrae sottostringa (da s.nome) di lunghezza 1 (a partire dalla prima lettera)
    @Query("""
        SELECT s FROM StatusObject s
        WHERE LOWER(SUBSTRING(s.nome,1,1)) NOT IN ('a','e','i','o','u')
    """)
    List<StatusObject> findByNomeStartingWithConsonant();

}
