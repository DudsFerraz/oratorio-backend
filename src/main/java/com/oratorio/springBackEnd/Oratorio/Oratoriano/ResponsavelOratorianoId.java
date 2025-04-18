package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import com.oratorio.springBackEnd.Documentos.CPF;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ResponsavelOratorianoId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private CPF responsavelId;
    private long oratorianoId;

    public ResponsavelOratorianoId() {}

    public ResponsavelOratorianoId(CPF responsavelId, long oratorianoId) {
        this.responsavelId = responsavelId;
        this.oratorianoId = oratorianoId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResponsavelOratorianoId that = (ResponsavelOratorianoId) o;
        return responsavelId == that.responsavelId && oratorianoId == that.oratorianoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(responsavelId, oratorianoId);
    }
}
