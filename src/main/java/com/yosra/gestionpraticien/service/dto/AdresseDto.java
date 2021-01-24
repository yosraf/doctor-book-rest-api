package com.yosra.gestionpraticien.service.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AdresseDto {

    private Long id;
    @NotNull(message = "description ne peut pas etre null")
    @Size(min = 10, max = 60, message = "description ne peut pas dépasser 60 caractéres")
    private String description;

    @NotNull(message = "code postal ne peut pas etre null")
    private int postalCode;

    @NotNull(message = "ville ne peut pas etre null")
    private String ville;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


}
