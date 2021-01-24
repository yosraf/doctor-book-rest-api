package com.yosra.gestionpraticien.service.interfaces;

import com.yosra.gestionpraticien.model.PraticienProjection;
import com.yosra.gestionpraticien.service.dto.ParticienDto;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IPartcienService {

    ParticienDto createParticien(ParticienDto particienDto);

    Set<ParticienDto> getAllParticiens(Pageable pageable);

    ParticienDto getParticienById(Long id);

    Set<ParticienDto> getParticienByName(String name);

    Set<ParticienDto> getParticienByLangue(String langue);

    Set<ParticienDto> getParticienBySpecialite(String specialite);

    ParticienDto updatePartcien(ParticienDto particienDto);

    void deletePartien(Long id);

    Set<PraticienProjection> getParticiens(String name, String specialite, String email);

}
