package com.oratorio.springBackEnd.Oratorio.Oratoriano;


import com.oratorio.springBackEnd.Documentos.CPF;
import com.oratorio.springBackEnd.Documentos.RG;
import com.oratorio.springBackEnd.Oratorio.AnoDeOratorio.AnoDeOratorio;
import com.oratorio.springBackEnd.Oratorio.Dia.Dia;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Entity
public class Oratoriano implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private LocalDate dataNascimento;

    @Column
    private CPF cpf;

    @Column
    private RG rg;

    @Embedded
    private Endereco endereco;
    @Column
    private String telefone;

    @Column
    private String escola;

    @Column
    private String anoEscola;

    @OneToMany(mappedBy = "oratoriano")
    private Set<ResponsavelOratoriano> responsaveis;

    @OneToOne//cascade?
    private FichaMedica fichaMedica;

    @OneToMany(mappedBy = "oratoriano")
    private Set<PresencaOratoriano> presencas;

    public Oratoriano() {}
    public Oratoriano(String nome) {
        setNome(nome);
        this.dataNascimento = null;
        this.fichaMedica = null;
        this.presencas = new HashSet<>();
        this.responsaveis = new HashSet<>();

    }
    public Oratoriano(String nome, LocalDate dataNascimento) {
        this(nome);
        setDataNascimento(dataNascimento);
    }


    public boolean podePiscina(){
        if(fichaMedica == null) return false;
        return fichaMedica.podePiscina();
    }
    public void addPresenca(Dia dia){
        presencas.add(new PresencaOratoriano(this,dia));
    }
    public void removePresenca(PresencaOratoriano presenca){
        presencas.remove(presenca);
    }
    public int idade(){
        if (this.dataNascimento == null) return -1;
        return (int) dataNascimento.until(LocalDate.now(), ChronoUnit.YEARS);
    }

    void setFichaMedica(FichaMedica fichaMedica) {
        this.fichaMedica = fichaMedica;
    }
    void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    void setResponsaveis(Set<ResponsavelOratoriano> responsaveis) {
        this.responsaveis = responsaveis;
    }
    void setCpf(CPF cpf) {
        this.cpf = cpf;
    }
    void setRg(RG rg) {
        this.rg = rg;
    }
    Endereco getEndereco() {
        return endereco;
    }
    FichaMedica getFichaMedica(){
        return fichaMedica;
    }

    public void setNome(String nome) {
        if(nome==null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        this.nome = nome;
    }
    public void setDataNascimento(LocalDate dataDeNascimento) {
        if(dataDeNascimento==null) throw new IllegalArgumentException("Data nula");
        if(dataDeNascimento.isAfter(LocalDate.now())) throw new IllegalArgumentException("Data não pode ser futura");
        this.dataNascimento = dataDeNascimento;
    }
    public String getNome() {
        return nome;
    }
    public long getId() {
        return id;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public String getFichaMedicaString() {
        return fichaMedica.toString();
    }
    public List<PresencaOratoriano> getPresencasTotais() {
        return new ArrayList<>(presencas);
    }
    public int getNumeroPresencasTotais(){
        return presencas.size();
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
    public CPF getCpf() {
        return cpf;
    }
    public RG getRg() {
        return rg;
    }
    public String getEnderecoString() {
        return endereco.toString();
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEscola() {
        return escola;
    }
    public void setEscola(String escola) {
        this.escola = escola;
    }
    public List<Responsavel> getResponsaveis() {
        List<Responsavel> res = new ArrayList<>();
        for (ResponsavelOratoriano ro : responsaveis) {
            res.add(ro.getResponsavel());
        }

        return res;
    }
    public String getAnoEscola() {
        return anoEscola;
    }
    public void setAnoEscola(String anoEscola) {
        this.anoEscola = anoEscola;
    }
    public List<ResponsavelOratoriano> getResponsaveisOratoriano() {
        return new ArrayList<>(responsaveis);
    }


    @Override
    public String toString(){
        String piscina = "NÃO";
        if(podePiscina()) piscina = "Sim";

        if(fichaMedica == null){
            return String.format("""
                %s (Oratoriano) | ID: %d
                Data de nascimento: ? - ? anos
                Presencas totais: %d | Piscina: %s
                """
                    ,nome,id,presencas.size(),piscina);
        }

        StringBuilder sb = new StringBuilder();
        for(ResponsavelOratoriano ro : responsaveis){
            sb.append(ro.getResponsavel().getNome());
            sb.append(" (");
            sb.append(ro.getParentesco());
            sb.append(")\n");
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("""
                %s (Oratoriano) | ID: %d
                Data de nascimento: %s - %d anos
                Presencas totais: %d | Piscina: %s
                
                CPF: %s
                RG: %s
                
                ----------Endereço----------
                %s
                ----------------------------
                
                Telefone: %s
                Escola: %s | Ano escolar: %s
                
                --------Responsáveis--------
                %s
                ----------------------------
                
                --------Ficha médica--------
                %s
                ----------------------------
                """
                ,nome,id, dataNascimento.format(dtf),this.idade(),presencas.size(),piscina,getCpf().getCpf(),
                getRg().getFullRg(),getEnderecoString(),getTelefone(),getEscola(),getAnoEscola(),sb,getFichaMedica());


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
