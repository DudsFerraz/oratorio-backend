package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import com.oratorio.springBackEnd.Oratorio.Dia.Dia;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class PresencaOratoriano implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PresencaOratorianoId id;

    @ManyToOne
    @MapsId("oratorianoId")
    private Oratoriano oratoriano;

    @ManyToOne
    @MapsId("diaId")
    private Dia dia;

    public PresencaOratoriano() {}
    public PresencaOratoriano(Oratoriano oratoriano, Dia dia) {
        this.dia = dia;
        this.oratoriano = oratoriano;
        this.id = new PresencaOratorianoId(oratoriano.getId(), dia.getId());
    }

    public PresencaOratorianoId getId() {
        return id;
    }

    public Oratoriano getOratoriano() {
        return oratoriano;
    }

    public Dia getDia() {
        return dia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PresencaOratoriano that = (PresencaOratoriano) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
