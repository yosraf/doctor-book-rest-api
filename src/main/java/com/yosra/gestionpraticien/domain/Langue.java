package com.yosra.gestionpraticien.domain;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "langues")
@EntityListeners(AuditingEntityListener.class)

public class Langue extends Audit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "langues_seq")
    @SequenceGenerator(name = "langues_seq", sequenceName = "langue_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "code ne peut pas etre null")
    @Column(name = "code")
    private String code;

    @NotNull(message = "libelle ne peut pas etre null")
    @Column(name = "libelle")
    private String libelle;

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "langues")
    private Set<Particien> particienList = new HashSet<>();


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

    public Set<Particien> getParticienList() {
        return particienList;
    }

    public void setParticienList(Set<Particien> particienList) {
        this.particienList = particienList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Langue)) return false;
        Langue langue = (Langue) o;
        return getId().equals(langue.getId()) &&
                getCode().equals(langue.getCode()) &&
                getLibelle().equals(langue.getLibelle());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getLibelle());
    }
}
