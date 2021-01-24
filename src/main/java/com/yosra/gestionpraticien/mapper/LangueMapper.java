package com.yosra.gestionpraticien.mapper;

import com.yosra.gestionpraticien.domain.Langue;
import com.yosra.gestionpraticien.service.dto.LangueDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LangueMapper {


    LangueDto langueTolangueDto(Langue langue);

    Langue langueDtoTolangue(LangueDto langueDto);

    Set<LangueDto> languesTolangueDtos(Set<Langue> langue);

    Set<Langue> languesDtoTolangues(Set<LangueDto> langueDto);
}
