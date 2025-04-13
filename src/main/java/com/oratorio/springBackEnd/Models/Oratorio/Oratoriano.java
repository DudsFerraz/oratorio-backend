package com.oratorio.springBackEnd.Models.Oratorio;


import com.oratorio.springBackEnd.Models.Oratorio.FichaMedica.FichaMedica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

//criar setFichaMedia
public class Oratoriano {
    private String nome;
    private final int id;
    private LocalDate dataDeNascimento;
    private FichaMedica fichaMedica;
    private int presencas;

    public Oratoriano(String nome) {
        setNome(nome);
        this.id = OratorianoIdGenerator.getId();
        this.dataDeNascimento = null;
        this.fichaMedica = null;
        this.presencas = 0;
    }

    public Oratoriano(String nome, LocalDate dataDeNascimento) {
        this(nome);
        setDataDeNascimento(dataDeNascimento);
    }
    public boolean podePiscina(){
        if(fichaMedica == null) return false;
        return fichaMedica.podePiscina();
    }

    @Override
    public String toString(){
        String piscina;
        if(podePiscina()){
            piscina = "Sim";
        }else{
            piscina = "NÃO";
        }


        if(dataDeNascimento == null){
            return String.format("""
                %s (Oratoriano) | ID: %d
                Data de nascimento: ? - ? anos
                Presencas: %d | Piscina: %s
                """
                    ,nome,id,presencas,piscina);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("""
                %s (Oratoriano) | ID: %d
                Data de nascimento: %s - %d anos
                Presencas: %d | Piscina: %s
                """
                ,nome,id,dataDeNascimento.format(dtf),this.idade(),presencas,piscina);
    }
    public void addPresenca(){
        this.presencas++;
    }
    public void removePresenca(){
        this.presencas--;
    }
    public int idade(){
        if (this.dataDeNascimento == null) return -1;
        return (int) dataDeNascimento.until(LocalDate.now(), ChronoUnit.YEARS);
    }
    public void setFichaMedica(FichaMedica fichaMedica) {
        this.fichaMedica = fichaMedica;
    }


    public void setFichaMedica(Oratoriano oratoriano, LocalDate dataDeNascimento, String CEP, String cidade, String bairro,
                               String rua, String numero, String telefone, String escola, boolean sabeNadar, boolean aptoAPiscina,
                               String nomeRes, CPF cpfRes, String parentescoRes, String nomeRes2, CPF cpfRes2,
                               String parentescoRes2, String nomeRes3, CPF cpfRes3, String parentescoRes3, String assinadoPor,
                               LocalDate date){

        this.fichaMedica = new FichaMedica(oratoriano, dataDeNascimento, CEP, cidade, bairro, rua, numero, telefone,
                escola, sabeNadar, aptoAPiscina, nomeRes, cpfRes, parentescoRes, nomeRes2,
                cpfRes2, parentescoRes2, nomeRes3, cpfRes3, parentescoRes3, assinadoPor,
                date);
    }
    public void setFichaMedica(Oratoriano oratoriano, LocalDate dataDeNascimento, String CEP, String cidade, String bairro,
                               String rua, String numero, String telefone, String escola, boolean sabeNadar, boolean aptoAPiscina,
                               String nomeRes, CPF cpfRes, String parentescoRes, String nomeRes2, CPF cpfRes2,
                               String parentescoRes2, String assinadoPor, LocalDate date){

        this.fichaMedica = new FichaMedica(oratoriano, dataDeNascimento, CEP, cidade, bairro, rua, numero, telefone,
                escola, sabeNadar, aptoAPiscina, nomeRes, cpfRes, parentescoRes, nomeRes2,
                cpfRes2, parentescoRes2, assinadoPor, date);
    }
    public void setFichaMedica(Oratoriano oratoriano, LocalDate dataDeNascimento, String CEP, String cidade, String bairro,
                               String rua, String numero, String telefone, String escola, boolean sabeNadar, boolean aptoAPiscina,
                               String nomeRes, CPF cpfRes, String parentescoRes, String assinadoPor, LocalDate date){

        this.fichaMedica = new FichaMedica(oratoriano, dataDeNascimento, CEP, cidade, bairro, rua, numero, telefone,
                escola, sabeNadar, aptoAPiscina, nomeRes, cpfRes, parentescoRes, assinadoPor, date);
    }


    public void setNome(String nome) {
        if(nome==null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        this.nome = nome;
    }
    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        if(dataDeNascimento==null) throw new IllegalArgumentException("Data nula");
        if(dataDeNascimento.isAfter(LocalDate.now())) throw new IllegalArgumentException("Data não pode ser futura");
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
    public FichaMedica getFichaMedica() {
        return fichaMedica;
    }
    public int getPresencas() {
        return presencas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Oratoriano that = (Oratoriano) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
