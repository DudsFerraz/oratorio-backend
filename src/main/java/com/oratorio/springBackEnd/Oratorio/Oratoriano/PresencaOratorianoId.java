package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class PresencaOratorianoId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private long oratorianoId;
    private LocalDate diaId;

    public PresencaOratorianoId() {}
    public PresencaOratorianoId(long oratorianoId, LocalDate diaId) {
        this.oratorianoId = oratorianoId;
        this.diaId = diaId;
    }

    public long getOratorianoId() {
        return oratorianoId;
    }

    public LocalDate getDiaId() {
        return diaId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PresencaOratorianoId that = (PresencaOratorianoId) o;
        return getOratorianoId() == that.getOratorianoId() && getDiaId() == that.getDiaId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOratorianoId(), getDiaId());
    }
}
