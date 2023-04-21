package com.example.challenge.repository;


import com.example.challenge.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {

    @Query(value = "SELECT * FROM persona WHERE id = :id",nativeQuery = true)
    Persona encontrarPersonaPorId(@Param("id")Long id);
}
