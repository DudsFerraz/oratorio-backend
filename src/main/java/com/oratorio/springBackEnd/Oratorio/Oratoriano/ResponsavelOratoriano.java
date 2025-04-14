package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class ResponsavelOratoriano implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @EmbeddedId
    private ResponsavelOratorianoId id;

    @ManyToOne
    @MapsId("responsavelId")
    private Responsavel responsavel;

    @ManyToOne
    @MapsId("oratorianoId")
    private Oratoriano oratoriano;

    private String parentesco;

    public ResponsavelOratoriano() {}
    public ResponsavelOratoriano(Responsavel responsavel, Oratoriano oratoriano, String parentesco) {
        this.responsavel = responsavel;
        this.oratoriano = oratoriano;
        this.parentesco = parentesco;
        this.id = new ResponsavelOratorianoId(responsavel.getCpf(), oratoriano.getId());
    }

    public ResponsavelOratorianoId getId() {
        return id;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public Oratoriano getOratoriano() {
        return oratoriano;
    }

    public String getParentesco() {
        return parentesco;
    }
}
