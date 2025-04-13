package com.oratorio.springBackEnd.Models.Oratorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Voluntario {
    private String nome;
    private final int id;
    private LocalDate dataDeNascimento;
    private int presencas;

    public Voluntario(String nome) {
        setNome(nome);
        this.id = VoluntarioIdGenerator.getId();
        this.dataDeNascimento = null;
        this.presencas = 0;
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
                Presencas: %d
                """, nome, id, presencas);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("""
                %s (Voluntário) | ID: %d
                Data de nascimento: %s - %d anos
                Presencas: %d
                """,
                nome, id, dataDeNascimento.format(dtf), this.idade(), presencas);
    }
    public void addPresenca() {
        this.presencas++;
    }
    public void removePresenca() {
        this.presencas--;
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
    public int getId() {
        return id;
    }
    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }
    public int getPresencas() {
        return presencas;
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
