package com.yosra.gestionpraticien.service.impl;

import com.yosra.gestionpraticien.config.ApiConstants;
import com.yosra.gestionpraticien.domain.Langue;
import com.yosra.gestionpraticien.exceptions.RessourceNotFoundException;
import com.yosra.gestionpraticien.mapper.LangueMapper;
import com.yosra.gestionpraticien.repository.LangueRepository;
import com.yosra.gestionpraticien.service.dto.LangueDto;
import com.yosra.gestionpraticien.service.interfaces.ILangueService;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class LangueServiceImpl implements ILangueService {

    private final LangueRepository langueRepository;

    private final LangueMapper langueMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(LangueServiceImpl.class);


    public LangueServiceImpl(LangueRepository repository, LangueMapper langueMapper) {
        this.langueRepository = repository;
        this.langueMapper = langueMapper;
    }

    @Override
    public Set<LangueDto> getAllLangues(Pageable pageable) {
        LOGGER.info("getting langue list");
        Set<Langue> langues = new HashSet<>(langueRepository.findAll());

        return langueMapper.languesTolangueDtos(langues);
    }

    @Override
    public LangueDto getLangueById(Long id) {
        LOGGER.info("getting langue by id");

        Langue langue = langueRepository.findById(id).orElseThrow(
                () -> {
                    LOGGER.error("could not find langue");
                    return new RessourceNotFoundException("aucune langue trouvée");
                }
        );
        return langueMapper.langueTolangueDto(langue);
    }

    @Override
    public LangueDto createLangue(LangueDto langueDto) {
        LOGGER.info("creating new langue");
        Langue langue = langueMapper.langueDtoTolangue(langueDto);
        return langueMapper.langueTolangueDto(langueRepository.save(langue));
    }

    @Override
    public void deleteLangue(Long id) {
        LOGGER.info("deleting langue");
        Langue langue = langueRepository.findById(id).orElseThrow(
                () -> {
                    LOGGER.error("could not find langue");
                    return new RessourceNotFoundException("aucune langue trouvée");
                }
        );
        langueRepository.delete(langue);

    }

    @Override
    public LangueDto updateLangue(LangueDto langueDto) {
        LOGGER.info("updating langue");
        Langue langue = langueRepository.findById(langueDto.getId()).orElseThrow(
                () -> {
                    LOGGER.error("could not find langue");
                    return new RessourceNotFoundException("aucune langue trouvée");
                }
        );
        langue.setId(langueDto.getId());
        langue.setCode(langueDto.getCode());
        langue.setLibelle(langueDto.getLibelle());
        langueRepository.save(langue);
        return langueMapper.langueTolangueDto(langue);
    }

    @Override
    public Object getLanguesPerCountry(String country) {
        LOGGER.info("getting langue from langues api");
        return Unirest.get(ApiConstants.LANGUE_END_POINT + "{et}")
                .header("Accept", "application/json")
                .routeParam("et", country).asString();
        //.asJson().getBody().getObject().get("languages");

    }
}
