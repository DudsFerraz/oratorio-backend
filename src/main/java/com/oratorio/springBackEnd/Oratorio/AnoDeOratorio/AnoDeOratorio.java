package com.oratorio.springBackEnd.Oratorio.AnoDeOratorio;


import com.oratorio.springBackEnd.Oratorio.Dia.Dia;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.Oratoriano;
import com.oratorio.springBackEnd.Oratorio.Voluntario.Voluntario;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

//remover Set<Oratorianos> e Set<Voluntarios>
@Entity
public class AnoDeOratorio implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @OneToMany(mappedBy = "anoDeOratorio")
    private Set<Dia> dias;

    public AnoDeOratorio() {}

    public AnoDeOratorio(String nome,LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) throw new IllegalArgumentException("Data nula");
        if (!dataInicio.isBefore(dataFim)) throw new IllegalArgumentException("Datas invalidas");
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        setNome(nome);

        dias = new HashSet<>();

        setDatas(this.dataInicio, this.dataFim);
    }



    private void setDatas(LocalDate dataInicio, LocalDate dataFim) {
        LocalDate currentDate = dataInicio;
        while (!currentDate.isAfter(dataFim)) {
            dias.add(new Dia(currentDate,this));
            currentDate = currentDate.plusDays(7);
        }
    }



    public void removeDia(LocalDate date) {
        if(date == null) throw new IllegalArgumentException("Data nula");
        Dia diaToRemove = null;
        for (Dia dia : dias) {
            if(dia.getDate().equals(date)) diaToRemove = dia; break;
        }
        if(diaToRemove == null) throw new IllegalArgumentException("Data não registrada");

        dias.remove(diaToRemove);
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public List<Dia> getDias() {
        return new ArrayList<>(dias);
    }
    public int getDiasCount() {
        return dias.size();
    }
    public long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if(nome != null && !nome.isBlank()) throw new IllegalArgumentException("Nome nulo");
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AnoDeOratorio that = (AnoDeOratorio) o;
        return getId() == that.getId();
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
