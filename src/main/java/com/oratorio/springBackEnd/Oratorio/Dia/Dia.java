package com.oratorio.springBackEnd.Oratorio.Dia;


import com.oratorio.springBackEnd.Oratorio.AnoDeOratorio.AnoDeOratorio;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.Oratoriano;
import com.oratorio.springBackEnd.Oratorio.Voluntario.Voluntario;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
public class Dia implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private LocalDate date;

    @ManyToOne
    private AnoDeOratorio anoDeOratorio;

    @OneToMany
    private Set<Oratoriano> oratorianos;

    @OneToMany
    private Set<Voluntario> voluntarios;

    public Dia(){}

    public Dia(LocalDate date, AnoDeOratorio anoDeOratorio) {
        if (date == null) throw new IllegalArgumentException("Data nula");
        this.date = date;
        this.oratorianos = new HashSet<>();
        this.voluntarios = new HashSet<>();
        this.anoDeOratorio = anoDeOratorio;
    }
    public void addOratoriano(Oratoriano oratoriano) {
        if (oratoriano == null) throw new IllegalArgumentException("Oratoriano nulo");

        if (!oratorianos.add(oratoriano)) throw new IllegalArgumentException("Oratoriano ja registrado");
    }
    public void removeOratoriano(Oratoriano oratoriano) {
        if (oratoriano == null) throw new IllegalArgumentException("Oratoriano nulo");

        if(!oratorianos.remove(oratoriano)) throw new IllegalArgumentException("Oratoriano nao encontrado");
    }
    public void addVoluntario(Voluntario voluntario) {
        if (voluntario == null) throw new IllegalArgumentException("Voluntario nulo");

        if (!voluntarios.add(voluntario)) throw new IllegalArgumentException("Voluntario ja registrado");
    }
    public void removeVoluntario(Voluntario voluntario) {
        if (voluntario == null) throw new IllegalArgumentException("Voluntario nulo");

        if(!voluntarios.remove(voluntario)) throw new IllegalArgumentException("Voluntario nao encontrado");
    }
    public int getVoluntariosCount() {
        return voluntarios.size();
    }
    public int getOratorianosCount() {
        return oratorianos.size();
    }
    public LocalDate getDate() {
        return date;
    }
    public String getDateAsString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(dtf);
    }
    public List<Oratoriano> getOratorianos() {
        return new ArrayList<>(oratorianos);
    }
    public List<Voluntario> getVoluntarios() {
        return new ArrayList<>(voluntarios);
    }
    public AnoDeOratorio getAnoDeOratorio() {
        return anoDeOratorio;
    }
    public LocalDate getId(){
        return getDate();
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dia dia = (Dia) o;
        return Objects.equals(getDate(), dia.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDate());
    }
}
