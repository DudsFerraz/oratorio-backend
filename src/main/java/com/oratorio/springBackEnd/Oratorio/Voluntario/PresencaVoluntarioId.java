package com.oratorio.springBackEnd.Oratorio.Voluntario;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

//classe embeddable para criacao do Id de PresencaVoluntario -> composto pelo id de um voluntario e de um dia
@Embeddable
public class PresencaVoluntarioId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private long voluntarioId;
    private LocalDate diaId;

    public PresencaVoluntarioId() {}
    public PresencaVoluntarioId(long voluntarioId, LocalDate diaId) {
        this.voluntarioId = voluntarioId;
        this.diaId = diaId;
    }

    public long getVoluntarioId() {
        return voluntarioId;
    }

    public LocalDate getDiaId() {
        return diaId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PresencaVoluntarioId that = (PresencaVoluntarioId) o;
        return getVoluntarioId() == that.getVoluntarioId() && getDiaId() == that.getDiaId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVoluntarioId(), getDiaId());
    }
}
