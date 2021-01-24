package com.yosra.gestionpraticien.mapper;

import com.yosra.gestionpraticien.domain.Particien;
import com.yosra.gestionpraticien.service.dto.ParticienDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {AdresseMapper.class, LangueMapper.class}, componentModel = "spring")
public interface ParticienMapper {


    ParticienDto particientoParticienDto(Particien particien);

    Particien particienDtotoParticien(ParticienDto particienDto);

    Set<ParticienDto> particienstoParticienDtos(Set<Particien> particien);

    Set<Particien> particienDtostoParticiens(Set<ParticienDto> particienDto);


}
