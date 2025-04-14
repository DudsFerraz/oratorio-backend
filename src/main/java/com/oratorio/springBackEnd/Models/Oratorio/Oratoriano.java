package com.oratorio.springBackEnd.Models.Oratorio;


import com.oratorio.springBackEnd.Models.Documentos.CPF;
import com.oratorio.springBackEnd.Models.Oratorio.FichaMedica.FichaMedica;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

//getPresencas
@Entity
public class Oratoriano implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column
    private LocalDate dataDeNascimento;

    @OneToOne//cascade?
    private FichaMedica fichaMedica;

    @OneToMany(mappedBy = "oratoriano")
    private Set<PresencaOratoriano> presencas;

    public Oratoriano() {}
    public Oratoriano(String nome) {
        setNome(nome);
        this.dataDeNascimento = null;
        this.fichaMedica = null;
        this.presencas = new HashSet<>();
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
                Presencas totais: %d | Piscina: %s
                """
                    ,nome,id,presencas.size(),piscina);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("""
                %s (Oratoriano) | ID: %d
                Data de nascimento: %s - %d anos
                Presencas totais: %d | Piscina: %s
                """
                ,nome,id,dataDeNascimento.format(dtf),this.idade(),presencas.size(),piscina);
    }
    public void addPresenca(Dia dia){
        presencas.add(new PresencaOratoriano(this,dia));
    }
    public void removePresenca(PresencaOratoriano presenca){
        presencas.remove(presenca);
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
    public List<PresencaOratoriano> getPresencasTotais() {
        return new ArrayList<>(presencas);
    }
    public int getNumeroPresencasTotais(){
        List<PresencaOratoriano> presencasTotais = getPresencasTotais();
        return presencasTotais.size();
    }
    public List<PresencaOratoriano> getPresencasAno(AnoDeOratorio anoDeOratorio) {
        List<PresencaOratoriano> presencasAno = new ArrayList<>();
        for (PresencaOratoriano presenca : presencas) {
            if(presenca.getDia().getAnoDeOratorio().equals(anoDeOratorio)) presencasAno.add(presenca);
        }

        return presencasAno;
    }
    public int getNumeroPresencasAno(AnoDeOratorio anoDeOratorio) {
        List<PresencaOratoriano> presencasAno = getPresencasAno(anoDeOratorio);
        return presencasAno.size();
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
