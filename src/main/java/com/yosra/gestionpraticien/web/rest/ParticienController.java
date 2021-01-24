package com.yosra.gestionpraticien.web.rest;

import com.yosra.gestionpraticien.model.PraticienProjection;
import com.yosra.gestionpraticien.service.dto.ParticienDto;
import com.yosra.gestionpraticien.service.interfaces.IPartcienService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("api/v1/particien")
public class ParticienController {

    private final IPartcienService partcienService;


    public ParticienController(IPartcienService service) {
        this.partcienService = service;

    }


    @PostMapping
    public ResponseEntity<ParticienDto> createParticien(@Valid @RequestBody ParticienDto particienDto) {


        return new ResponseEntity<>(partcienService.createParticien(particienDto), HttpStatus.CREATED);

    }

    @GetMapping
    public Set<ParticienDto> getAllParticiens(Pageable pageable) {
        return partcienService.getAllParticiens(pageable);

    }

    @GetMapping("{id}")
    public ResponseEntity<ParticienDto> getParticienById(@PathVariable Long id) {

        return new ResponseEntity<>(partcienService.getParticienById(id), HttpStatus.OK);


    }

    @GetMapping("byName/{name}")
    public ResponseEntity<Set<ParticienDto>> getParticienByName(@PathVariable String name) {

        return new ResponseEntity<>(partcienService.getParticienByName(name), HttpStatus.OK);

    }

    @GetMapping("byLangue/{langue}")
    public ResponseEntity<Set<ParticienDto>> getParticienByLangue(@PathVariable String langue) {

        return new ResponseEntity<>(partcienService.getParticienByLangue(langue), HttpStatus.OK);

    }

    @GetMapping("bySpecialite/{specialite}")
    ResponseEntity<Set<ParticienDto>> getParticienBySpecialite(@PathVariable String specialite) {
        return new ResponseEntity<>(partcienService.getParticienBySpecialite(specialite), HttpStatus.OK);

    }

    @GetMapping("all")
    ResponseEntity<Set<PraticienProjection>> getParticiens(@RequestParam String name, @RequestParam String specialite, @RequestParam String email) {
        return new ResponseEntity<>(partcienService.getParticiens(name, specialite, email), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<ParticienDto> updatePartcien(@Valid @RequestBody ParticienDto particienDto) {

        return new ResponseEntity<>(partcienService.updatePartcien(particienDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public void deletePartien(@PathVariable Long id) {

        partcienService.deletePartien(id);

    }


}
