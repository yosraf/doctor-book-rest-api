package com.yosra.gestionpraticien.service.interfaces;

import com.yosra.gestionpraticien.service.dto.LangueDto;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ILangueService {
    Set<LangueDto> getAllLangues(Pageable pageable);

    LangueDto getLangueById(Long id);

    LangueDto createLangue(LangueDto langueDto);

    void deleteLangue(Long id);

    LangueDto updateLangue(LangueDto langueDto);

    Object getLanguesPerCountry(String country);
}
