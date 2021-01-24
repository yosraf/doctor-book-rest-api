package com.yosra.gestionpraticien.web.rest;

import com.yosra.gestionpraticien.service.dto.LangueDto;
import com.yosra.gestionpraticien.service.interfaces.ILangueService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("api/v1/langue")
public class LangueController {

    private final ILangueService service;

    public LangueController(ILangueService service) {
        this.service = service;
    }


    @GetMapping
    public Set<LangueDto> getAllLangues(Pageable pageable) {
        return service.getAllLangues(pageable);
    }

    @GetMapping("country/{country}")
    public Object getLanguesPerCountry(@PathVariable String country) {
        return service.getLanguesPerCountry(country);
    }

    @GetMapping("{id}")
    public ResponseEntity<LangueDto> getLangueById(@PathVariable Long id) {
        LangueDto langue = service.getLangueById(id);
        return new ResponseEntity<>(langue, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LangueDto> createLangue(@Valid @RequestBody LangueDto langueDto) {

        return new ResponseEntity<>(service.createLangue(langueDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LangueDto> updateLangue(@Valid @RequestBody LangueDto langueDto) {
        LangueDto langue = service.updateLangue(langueDto);
        return new ResponseEntity<>(langue, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public void deleteLangue(@PathVariable Long id) {
        service.deleteLangue(id);

    }

}
