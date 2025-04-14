package com.oratorio.springBackEnd.Models.Oratorio;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

//getPresencas
@Entity
public class Voluntario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //id gerado automaticamente pelo postgre
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //coluna not null
    @Column(nullable = false)
    private String nome;

    //coluna sem constraints
    @Column
    private LocalDate dataDeNascimento;

    //relacao OneToMany com a entidade PresencaVoluntario //mappedBy indica que o outro lado e dono da relacao
    @OneToMany(mappedBy = "voluntario")
    private Set<PresencaVoluntario> presencas;

    public Voluntario() {}
    public Voluntario(String nome) {
        setNome(nome);
        this.dataDeNascimento = null;
        this.presencas = new HashSet<>();
    }
    public Voluntario(String nome, LocalDate dataDeNascimento) {
        this(nome);
        setDataDeNascimento(dataDeNascimento);
    }



    @Override
    public String toString() {
        if (dataDeNascimento == null) {
            return String.format("""
                %s (Voluntário)| ID: %d
                Data de nascimento: ? - ? anos
                Presencas totais: %d
                """, nome, id, presencas.size());
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("""
                %s (Voluntário) | ID: %d
                Data de nascimento: %s - %d anos
                Presencas totais: %d
                """,
                nome, id, dataDeNascimento.format(dtf), this.idade(), presencas.size());
    }
    public void addPresenca(Dia dia) {
        presencas.add(new PresencaVoluntario(this,dia));
    }
    public void removePresenca(PresencaVoluntario presenca) {
        presencas.remove(presenca);
    }
    public int idade() {
        if (this.dataDeNascimento == null) return -1;
        return (int) dataDeNascimento.until(LocalDate.now(), ChronoUnit.YEARS);
    }
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        this.nome = nome;
    }
    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        if (dataDeNascimento == null) throw new IllegalArgumentException("Data nula");
        if (dataDeNascimento.isAfter(LocalDate.now())) throw new IllegalArgumentException("Data não pode ser futura");
        this.dataDeNascimento = dataDeNascimento;
    }
    public String getNome() {
        return nome;
    }
    public long getId() {
        return id;
    }
    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }
    public List<PresencaVoluntario> getPresencasTotais() {
        return new ArrayList<>(presencas);
    }
    public int getNumeroPresencasTotais(){
        List<PresencaVoluntario> presencasTotais = getPresencasTotais();
        return presencasTotais.size();
    }
    public List<PresencaVoluntario> getPresencasAno(AnoDeOratorio anoDeOratorio) {
        List<PresencaVoluntario> presencasAno = new ArrayList<>();
        for (PresencaVoluntario presenca : presencas) {
            if(presenca.getDia().getAnoDeOratorio().equals(anoDeOratorio)) presencasAno.add(presenca);
        }

        return presencasAno;
    }
    public int getNumeroPresencasAno(AnoDeOratorio anoDeOratorio) {
        List<PresencaVoluntario> presencasAno = getPresencasAno(anoDeOratorio);
        return presencasAno.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Voluntario that = (Voluntario) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
