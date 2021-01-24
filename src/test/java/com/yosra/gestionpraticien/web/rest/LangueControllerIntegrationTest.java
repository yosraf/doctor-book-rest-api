package com.yosra.gestionpraticien.web.rest;

import com.yosra.gestionpraticien.service.interfaces.ILangueService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LangueControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private ILangueService langueService;
    private LangueController langueController;
    private final Long ID = 1L;

    public void setUp() {
        MockitoAnnotations.initMocks(this);

        langueController = new LangueController(langueService);
        mockMvc = MockMvcBuilders.standaloneSetup(langueController).build();


    }

    @Test
    @Sql({"classpath:createLangue.sql", "classpath:insertLangue.sql"})

    public void getLangues() throws Exception {
        mockMvc.perform(get("/api/v1/langue/{ID}", ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("FR")));
        verify(langueService, times(1)).getLangueById(ID);

    }
}
