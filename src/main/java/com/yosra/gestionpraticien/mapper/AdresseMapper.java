package com.yosra.gestionpraticien.mapper;

import com.yosra.gestionpraticien.domain.Adresse;
import com.yosra.gestionpraticien.service.dto.AdresseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")

public interface AdresseMapper {


    AdresseDto adresseToadressDto(Adresse adresse);

    Adresse adresseDtoToadress(AdresseDto adresseDto);

    Set<Adresse> addressesDtoToAdresses(Set<AdresseDto> adresseDtos);

    Set<AdresseDto> addressesToAdressesDto(Set<Adresse> adresses);

}
