package com.yosra.gestionpraticien.service.dto;

import javax.validation.constraints.NotNull;

public class LangueDto {

    private Long id;

    @NotNull(message = "code ne peut pas etre null")
    private String code;

    @NotNull(message = "code ne peut pas etre null")
    private String libelle;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


}
