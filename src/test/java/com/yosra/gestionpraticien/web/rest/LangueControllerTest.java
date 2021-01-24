package com.yosra.gestionpraticien.web.rest;

import com.yosra.gestionpraticien.exceptions.RessourceNotFoundException;
import com.yosra.gestionpraticien.service.dto.LangueDto;
import com.yosra.gestionpraticien.service.interfaces.ILangueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class LangueControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ILangueService langueService;
    private LangueController langueController;

    private final Long ID = 2L;
    private LangueDto langueDto;

    LangueDto createLangueDto() {
        langueDto = new LangueDto();
        langueDto.setId(ID);
        langueDto.setLibelle("Arabe");
        langueDto.setCode("AR");
        return langueDto;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        langueController = new LangueController(langueService);
        mockMvc = MockMvcBuilders.standaloneSetup(langueController).build();

        langueDto = createLangueDto();
    }

    @Test
    public void getLangueById() throws Exception {

        when(langueService.getLangueById(ID)).thenReturn(langueDto);
        mockMvc.perform(get("/api/v1/langue/{ID}", ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("AR")));
        verify(langueService, times(1)).getLangueById(ID);
    }

    @Test
    public void getLangueByIdNotFound() throws Exception {
        when(langueService.getLangueById(ID)).thenThrow(new RessourceNotFoundException("aucune langue trouvée"));
        mockMvc.perform(get("/api/v1/langue/{ID}", ID))
                .andExpect(status().isNotFound());
        verify(langueService, times(1)).getLangueById(ID);
    }

    @Test
    public void createLangue() throws Exception {
        when(langueService.createLangue(langueDto)).thenReturn(langueDto);
        mockMvc.perform(post("/api/v1/langue")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(langueDto))).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("AR"));


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
