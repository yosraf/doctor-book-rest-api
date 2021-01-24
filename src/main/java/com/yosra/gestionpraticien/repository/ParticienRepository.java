package com.yosra.gestionpraticien.repository;

import com.yosra.gestionpraticien.domain.Particien;
import com.yosra.gestionpraticien.model.PraticienProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ParticienRepository extends JpaRepository<Particien, Long> {

    @Query(value = "SELECT * FROM particiens p " +
            "WHERE ( p.first_name = :name OR p.last_name = :name" +
            " OR concat(p.first_name,' ',p.last_name) = :name  OR concat(p.last_name,' ',p.first_name) = :name" +
            " OR concat(p.first_name,' ',p.last_name) ~ ALL(STRING_TO_ARRAY(:name,' ')))", nativeQuery = true
    )
    Set<Particien> findAllByName(String name);

    @Query(value = "SELECT * FROM particiens p " +
            " INNER JOIN particien_langue ON p.id = particien_langue.particien_id" +
            " INNER JOIN langues ON langues.id = particien_langue.langue_id" +
            " WHERE langues.code = :langue OR langues.libelle= :langue", nativeQuery = true
    )
    Set<Particien> findAllByLangue(String langue);

    @Query(value = "SELECT * FROM particiens p " +
            " INNER JOIN particien_specialite ON p.id = particien_specialite.particien_id" +
            " WHERE nom_specialite = :specialite", nativeQuery = true
    )
    Set<Particien> findAllBySpecialite(String specialite);

    @Query(value = "SELECT p.id as id,p.first_name as firstName,p.last_name as lastName,p.email as email FROM particiens p " +
            " INNER JOIN particien_specialite ON p.id = particien_specialite.particien_id" +
            " WHERE concat(p.first_name,' ',p.last_name) = :name AND nom_specialite = :specialite " +
            "AND p.email= :email", nativeQuery = true)
    Set<PraticienProjection> findParticiens(String name, String specialite, String email);


}
