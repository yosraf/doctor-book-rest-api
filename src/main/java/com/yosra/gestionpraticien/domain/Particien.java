package com.yosra.gestionpraticien.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "particiens")

public class Particien extends Audit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "particiens_seq")
    @SequenceGenerator(name = "particiens_seq", sequenceName = "particien_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "prenom ne peut pas etre null")
    @Size(min = 4, max = 60, message = "prenom ne doit pas depasser 60 caracteres")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "nom ne peut pas etre null")
    @Size(min = 4, max = 60, message = "nom ne peut pas depasser 60 caracteres ")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "date de naissance ne peut pas etre null")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull(message = "email ne peut pas etre null")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = " entrer un email valide")
    @Column(name = "email", unique = true)
    private String Email;

    @CollectionTable(name = "particien_specialite",
            joinColumns = @JoinColumn(name = "particien_id"))
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "nom_specialite")
    private Set<Specialite> speciality = new HashSet<>();

    @NotNull(message = "numero ne peut pas etre null")
    @Column(name = "phone")
    private String phone;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "particien_langue",
            joinColumns = {@JoinColumn(name = "particien_id")},
            inverseJoinColumns = {@JoinColumn(name = "langue_id")})
    private Set<Langue> langues = new HashSet<>();


    @OneToMany(mappedBy = "particien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Adresse> adresses = new HashSet<>();


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

    public Set<Adresse> getAdresses() {
        return adresses;
    }

    public void setAdresses(Set<Adresse> adresses) {
        this.adresses = adresses;
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

    public Set<Langue> getLangues() {
        return langues;
    }

    public void setLangues(Set<Langue> langues) {
        this.langues = langues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Particien)) return false;
        Particien particien = (Particien) o;
        return getId().equals(particien.getId()) &&
                getFirstName().equals(particien.getFirstName()) &&
                getLastName().equals(particien.getLastName()) &&
                getBirthDate().equals(particien.getBirthDate()) &&
                getEmail().equals(particien.getEmail()) &&
                getSpeciality().equals(particien.getSpeciality()) &&
                getPhone().equals(particien.getPhone()) &&
                getLangues().equals(particien.getLangues()) &&
                getAdresses().equals(particien.getAdresses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getBirthDate(), getEmail(), getSpeciality(), getPhone(), getLangues(), getAdresses());
    }
}
