package com.yosra.gestionpraticien.service.dto;


import com.yosra.gestionpraticien.domain.Specialite;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;


public class ParticienDto {

    private Long id;
    @NotNull(message = "prenom ne peut pas etre null")
    @Size(min = 4, max = 60, message = "prenom ne peut pas depasser 60 caracteres")
    private String firstName;

    @NotNull(message = "enom ne peut pas etre null")
    @Size(min = 4, max = 60, message = "nom ne peut pas depasser 60 caracteres")
    private String lastName;

    @NotNull(message = "date de naissance ne peut pas etre null")
    private LocalDate birthDate;

    @NotNull(message = "email ne peut pas etre null")
    @Email(message = " enter email valide")
    private String Email;

    @NotNull(message = "telephone ne peut pas etre null")
    private String phone;

    @NotNull(message = "specialité ne peut pas etre null")
    private Set<Specialite> speciality;

    @NotNull(message = "langues ne peut pas etre null")
    private Set<LangueDto> langues;

    @NotNull(message = "addresses ne peut pas etre null")
    private Set<AdresseDto> adresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Set<Specialite> getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Set<Specialite> speciality) {
        this.speciality = speciality;
    }

    public Set<LangueDto> getLangues() {
        return langues;
    }

    public void setLangues(Set<LangueDto> langues) {
        this.langues = langues;
    }

    public Set<AdresseDto> getAdresses() {
        return adresses;
    }

    public void setAdresses(Set<AdresseDto> adresses) {
        this.adresses = adresses;
    }


}
