package com.oratorio.springBackEnd.Oratorio.Voluntario;

import com.oratorio.springBackEnd.Oratorio.Dia.Dia;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class PresencaVoluntario implements Serializable {
   @Serial
   private static final long serialVersionUID = 1L;

   //utiliza PresencaVoluntarioId como id composto
   @EmbeddedId
   private PresencaVoluntarioId id;

   @ManyToOne
   //relacao ManyToOne com a entidade Voluntario, lado many (este aqui) e dono da relacao
   @MapsId("voluntarioId")
   //MapsId sinaliza que este sera o atributo "voluntarioId" vindo de PresencaVoluntarioId
   private Voluntario voluntario;

   @ManyToOne
   //Relacao unidirecional com a entidade Dia
   @MapsId("diaId")
   //MapsId sinaliza que este sera o atributo "diaId" vindo de PresencaVoluntarioId
   private Dia dia;

   public PresencaVoluntario() {}
   public PresencaVoluntario(Voluntario voluntario, Dia dia) {
       this.voluntario = voluntario;
       this.dia = dia;
       this.id = new PresencaVoluntarioId(voluntario.getId(), dia.getId());
   }

    public PresencaVoluntarioId getId() {
        return id;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public Dia getDia() {
        return dia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PresencaVoluntario that = (PresencaVoluntario) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
