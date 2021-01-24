package com.yosra.gestionpraticien.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "adresses")
@EntityListeners(AuditingEntityListener.class)

public class Adresse extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_seq")
    @SequenceGenerator(name = "addresses_seq", sequenceName = "address_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "Description ne peut pas etre null")
    @Size(min = 10, max = 60, message = "Description ne doit pas depasser 60 caracteres")
    @Column(name = "description")
    private String description;

    @NotNull(message = "code postal ne peut pas etre null")
    @Column(name = "postal_code")
    private int postalCode;

    @NotNull(message = "ville ne peut pas etre null")
    @Column(name = "ville")
    private String ville;

    @ManyToOne
    @JoinColumn(name = "particien_id", nullable = false)
    private Particien particien;


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

    public Particien getParticien() {
        return particien;
    }

    public void setParticien(Particien particien) {
        this.particien = particien;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adresse)) return false;
        Adresse adresse = (Adresse) o;
        return getPostalCode() == adresse.getPostalCode() &&

                getDescription().equals(adresse.getDescription()) &&
                getVille().equals(adresse.getVille());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getPostalCode(), getVille());
    }
}
