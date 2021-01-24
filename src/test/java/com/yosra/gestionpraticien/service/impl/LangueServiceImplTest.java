package com.yosra.gestionpraticien.service.impl;

import com.yosra.gestionpraticien.domain.Langue;
import com.yosra.gestionpraticien.exceptions.RessourceNotFoundException;
import com.yosra.gestionpraticien.mapper.LangueMapper;
import com.yosra.gestionpraticien.repository.LangueRepository;
import com.yosra.gestionpraticien.service.dto.LangueDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class LangueServiceImplTest {

    private LangueServiceImpl langueService;

    @Mock
    private LangueRepository langueRepository;

    @Mock
    private LangueMapper langueMapper;

    private final Long ID = 1L;
    private LangueDto langueDto;
    private Langue langue;


    private LangueDto createLangueDto() {
        langueDto = new LangueDto();
        langueDto.setId(ID);
        langueDto.setCode("FR");
        langueDto.setLibelle("Français");
        return langueDto;
    }

    private Langue createLangueEntity() {
        langue = new Langue();
        langue.setId(ID);
        langue.setCode("FR");
        langue.setLibelle("Français");
        return langue;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        langueService = new LangueServiceImpl(langueRepository, langueMapper);
        langueDto = createLangueDto();
        langue = createLangueEntity();
    }


    @Test
    public void getLangueById() {

        when(langueRepository.findById(ID)).thenReturn(Optional.of(langue));
        when(langueMapper.langueTolangueDto(langue)).thenReturn(langueDto);
        LangueDto result = langueService.getLangueById(ID);
        Assert.assertNotNull(result);
        Assert.assertSame(result.getCode(), langueDto.getCode());
        Assert.assertSame(result.getId(), langueDto.getId());
    }

    @Test(expected = RessourceNotFoundException.class)
    public void getLangueByIdNotFound() {
        when(langueRepository.findById(ID)).thenThrow(new RessourceNotFoundException("aucune langue trouvée"));
        LangueDto result = langueService.getLangueById(ID);
        Assert.assertNull(result);
    }

    @Test
    public void createLangue() {

        when(langueRepository.save(langue)).thenReturn(langue);
        when(langueMapper.langueDtoTolangue(langueDto)).thenReturn(langue);
        when(langueMapper.langueTolangueDto(langue)).thenReturn(langueDto);
        LangueDto result = langueService.createLangue(langueDto);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, langueDto);
    }


}
