package com.yosra.gestionpraticien.repository;

import com.yosra.gestionpraticien.domain.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresseRepository extends JpaRepository<Adresse, Long> {

}
