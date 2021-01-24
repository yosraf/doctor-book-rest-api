package com.yosra.gestionpraticien.service.impl;

import com.yosra.gestionpraticien.domain.Adresse;
import com.yosra.gestionpraticien.domain.Particien;
import com.yosra.gestionpraticien.exceptions.RessourceNotFoundException;
import com.yosra.gestionpraticien.mapper.AdresseMapper;
import com.yosra.gestionpraticien.mapper.LangueMapper;
import com.yosra.gestionpraticien.mapper.ParticienMapper;
import com.yosra.gestionpraticien.model.PraticienProjection;
import com.yosra.gestionpraticien.repository.ParticienRepository;
import com.yosra.gestionpraticien.service.dto.ParticienDto;
import com.yosra.gestionpraticien.service.interfaces.IPartcienService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class ParticienServiceImpl implements IPartcienService {

    private final ParticienRepository particienRepository;
    private final AdresseMapper adresseMapper;
    private final LangueMapper langueMapper;
    private final ParticienMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ParticienServiceImpl.class);

    public ParticienServiceImpl(ParticienRepository repository, AdresseMapper adresseMapper, LangueMapper langueMapper, ParticienMapper mapper) {
        this.particienRepository = repository;
        this.adresseMapper = adresseMapper;
        this.langueMapper = langueMapper;
        this.mapper = mapper;
    }

    @Override
    public ParticienDto createParticien(ParticienDto particienDto) {
        LOGGER.info("creating a particien");
        Particien particien = mapper.particienDtotoParticien(particienDto);
        Set<Adresse> adresses = new HashSet<>();
        particienDto.getAdresses().forEach(
                adresse -> {
                    Adresse adresse1 = adresseMapper.adresseDtoToadress(adresse);
                    adresse1.setParticien(particien);
                    adresses.add(adresse1);
                }
        );
        particien.setAdresses(adresses);
        Particien particien1 = particienRepository.save(particien);
        return mapper.particientoParticienDto(particien1);
    }

    @Override
    public Set<ParticienDto> getAllParticiens(Pageable pageable) {

        LOGGER.info("getting all particiens");
        Set<Particien> particienList = new HashSet<>(particienRepository.findAll());
        return mapper.particienstoParticienDtos(particienList);

    }

    @Override
    public Set<PraticienProjection> getParticiens(String name, String specialite, String email) {
        LOGGER.info("getting particien list");
        return new HashSet<>(particienRepository.findParticiens(name, specialite, email));


    }

    @Override
    public ParticienDto getParticienById(Long id) {

        Particien particien = particienRepository.findById(id).orElseThrow(

                () -> {
                    LOGGER.error("no particien found");

                    return new RessourceNotFoundException("aucun particien trouvé");
                }
        );
        LOGGER.info("getting a particien");
        return mapper.particientoParticienDto(particien);

    }

    @Override
    public Set<ParticienDto> getParticienByName(String name) {
        LOGGER.info("getting particien by name");
        Set<Particien> particiens = particienRepository.findAllByName(name);

        return mapper.particienstoParticienDtos(particiens);
    }

    @Override
    public Set<ParticienDto> getParticienByLangue(String langue) {
        LOGGER.info("getting particien by langue");
        Set<Particien> particiens = particienRepository.findAllByLangue(langue);
        return mapper.particienstoParticienDtos(particiens);

    }

    @Override
    public Set<ParticienDto> getParticienBySpecialite(String specialite) {
        LOGGER.info("getting particien by speciality");
        Set<Particien> particiens = particienRepository.findAllBySpecialite(specialite);
        return mapper.particienstoParticienDtos(new HashSet<>(particiens));
    }

    @Override
    public ParticienDto updatePartcien(ParticienDto particienDto) {
        LOGGER.info("updating particien");

        Particien particien = particienRepository.findById(particienDto.getId()).orElseThrow(

                () -> {
                    LOGGER.info("could not find particien to update");
                    return new RessourceNotFoundException("aucun particien trouvé");
                }
        );

        particien.setFirstName(particienDto.getFirstName());
        particien.setLastName(particienDto.getFirstName());
        particien.setBirthDate(particienDto.getBirthDate());
        particien.setEmail(particienDto.getEmail());
        particien.setLangues(langueMapper.languesDtoTolangues(new HashSet<>(particienDto.getLangues())));
        Set<Adresse> adresses = adresseMapper.addressesDtoToAdresses(new HashSet<>(particienDto.getAdresses()));
        adresses.forEach(adresse -> {
            adresse.setParticien(particien);


        });
        particien.setAdresses(adresses);


        particienRepository.save(particien);
        return mapper.particientoParticienDto(particien);

    }

    @Override
    public void deletePartien(Long id) {
        LOGGER.info("deleting a particien");
        Particien particien = particienRepository.findById(id).orElseThrow(
                () -> {
                    LOGGER.info("could not find a particien to delete");
                    return new RessourceNotFoundException("aucun praticien trouvé");
                }
        );

        particienRepository.delete(particien);

    }
}
