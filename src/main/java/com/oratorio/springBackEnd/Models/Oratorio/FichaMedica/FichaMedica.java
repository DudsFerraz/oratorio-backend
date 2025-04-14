package com.oratorio.springBackEnd.Models.Oratorio.FichaMedica;

import com.oratorio.springBackEnd.Models.Documentos.CPF;
import com.oratorio.springBackEnd.Models.Oratorio.Oratoriano;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//refatorar List<> para Set<>
@Entity
public class FichaMedica implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;
    private final Oratoriano oratoriano;
    private LocalDate dataDeNascimento;
    private final Endereco endereco;
    private String telefone;
    private String escola;
    private final AutorizacaoPiscina autorizacaoPiscina;
    private final Set<Responsavel> responsaveis;

    public FichaMedica() {}
    public FichaMedica(Oratoriano oratoriano, LocalDate dataDeNascimento, String CEP, String cidade, String bairro,
                       String rua, String numero, String telefone, String escola, boolean sabeNadar, boolean aptoAPiscina,
                       String nomeRes, CPF cpfRes, String parentescoRes, String assinadoPor, LocalDate date) {

        this.oratoriano = oratoriano;
        setDataDeNascimento(dataDeNascimento);
        oratoriano.setDataDeNascimento(dataDeNascimento);

        this.endereco = new Endereco();
        setEndereco(CEP, cidade, bairro, rua, numero);

        setTelefone(telefone);
        setEscola(escola);

        this.responsaveis = new HashSet<>();
        addResponsavel(nomeRes, cpfRes, parentescoRes);

        this.autorizacaoPiscina = new AutorizacaoPiscina();
        autorizacaoPiscina.setSabeNadar(sabeNadar);
        autorizacaoPiscina.setAptoAPiscina(aptoAPiscina);
        autorizacaoPiscina.setData(date);

        Responsavel temp = getResponsavelbyNome(assinadoPor);
        if(temp!=null) autorizacaoPiscina.setAssinadoPor(temp);
    }
    public FichaMedica(Oratoriano oratoriano, LocalDate dataDeNascimento, String CEP, String cidade, String bairro,
                       String rua, String numero, String telefone, String escola, boolean sabeNadar, boolean aptoAPiscina,
                       String nomeRes, CPF cpfRes, String parentescoRes, String nomeRes2, CPF cpfRes2,
                       String parentescoRes2, String assinadoPor, LocalDate date) {

        this(oratoriano, dataDeNascimento, CEP, cidade, rua, bairro, numero, telefone, escola, sabeNadar, aptoAPiscina,
                nomeRes, cpfRes, parentescoRes, assinadoPor, date);
        addResponsavel(nomeRes2, cpfRes2, parentescoRes2);
        Responsavel temp = getResponsavelbyNome(assinadoPor);
        if(temp!=null) autorizacaoPiscina.setAssinadoPor(temp);
    }
    public FichaMedica(Oratoriano oratoriano, LocalDate dataDeNascimento, String CEP, String cidade, String bairro,
                       String rua, String numero, String telefone, String escola, boolean sabeNadar, boolean aptoAPiscina,
                       String nomeRes, CPF cpfRes, String parentescoRes, String nomeRes2, CPF cpfRes2,
                       String parentescoRes2, String nomeRes3, CPF cpfRes3, String parentescoRes3, String assinadoPor,
                       LocalDate date) {

        this(oratoriano, dataDeNascimento, CEP, cidade, rua, bairro, numero, telefone, escola, sabeNadar, aptoAPiscina,
                nomeRes, cpfRes, parentescoRes, assinadoPor, date);
        addResponsavel(nomeRes2, cpfRes2, parentescoRes2);
        addResponsavel(nomeRes3, cpfRes3, parentescoRes3);
        Responsavel temp = getResponsavelbyNome(assinadoPor);
        if(temp!=null) autorizacaoPiscina.setAssinadoPor(temp);
    }





    private Responsavel getResponsavelbyNome(String nomeRes) {
        for (Responsavel responsavel : responsaveis) {
            if (responsavel.getNome().equalsIgnoreCase(nomeRes)) {
                return responsavel;
            }
        }
        return null;
    }




    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }
    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        if (dataDeNascimento == null) throw new IllegalArgumentException("Data nula");
        if (dataDeNascimento.isAfter(LocalDate.now())) throw new IllegalArgumentException("Data não pode ser futura");
        this.dataDeNascimento = dataDeNascimento;
    }
    public String getEndereco() {
        return endereco.toString();
    }
    public void setEndereco(String CEP, String cidade, String bairro, String rua, String numero) {
        this.endereco.setCEP(CEP);
        this.endereco.setCidade(cidade);
        this.endereco.setBairro(bairro);
        this.endereco.setRua(rua);
        this.endereco.setNumero(numero);
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        if (telefone == null) throw new IllegalArgumentException("Telefone nulo");
        this.telefone = telefone;
    }
    public String getEscola() {
        return escola;
    }
    public void setEscola(String escola) {
        if (escola == null) throw new IllegalArgumentException("Escola nula");
        this.escola = escola;
    }
    public List<String> getResponsaveis() {
        List<String> responsaveisStr = new ArrayList<>();
        for (Responsavel responsavel : responsaveis) {
            responsaveisStr.add(responsavel.toString());
        }
        return responsaveisStr;
    }
    public void addResponsavel(String nomeRes, CPF cpfRes, String parentescoRes) {
        if (responsaveis.size() >= 3) throw new IllegalStateException("Maximo 3 responsáveis permitidos");
        responsaveis.add(new Responsavel(nomeRes, cpfRes, parentescoRes, this.oratoriano));
    }
    public void removeResponsavel(CPF cpfRes) {
        if(cpfRes==null) throw new IllegalArgumentException("CPF nulo");
        Responsavel resToRemove = null;
        for (Responsavel r : responsaveis) {
            if (r.getCpf().equals(cpfRes)) resToRemove = r; break;
        }
        if (resToRemove==null) throw new IllegalArgumentException("Este responsavel nao existe");

        responsaveis.remove(resToRemove);
    }
    public String getAutorizacaoPiscina() {
        return autorizacaoPiscina.toString();
    }
    public void setAutorizacaoPiscina(boolean sabeNadar, boolean aptoAPiscina, String assinadoPor, LocalDate data) {
        autorizacaoPiscina.setSabeNadar(sabeNadar);
        autorizacaoPiscina.setAptoAPiscina(aptoAPiscina);

        Responsavel temp = getResponsavelbyNome(assinadoPor);
        if(temp!=null) autorizacaoPiscina.setAssinadoPor(temp);
        autorizacaoPiscina.setData(data);
    }
    public boolean podePiscina() {
        return autorizacaoPiscina.podePiscina();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Responsavel responsavel : responsaveis) {
            sb.append(responsavel.toString());
        }

        Responsavel temp = this.autorizacaoPiscina.getAssinadoPor();
        String assinadoPor;
        if(temp!=null) assinadoPor = temp.getNome(); else assinadoPor = "";

        return String.format("""
                ----------------------FICHA MEDICA----------------------
                
                %s
                Escola: %s
                
                %s
                
                %s
                
                Telefone para contato: %s
                
                Piscina --> %s
                
                Assinado por: %s
                ----------------------FICHA MEDICA----------------------
                """,
                oratoriano.toString(),escola,endereco.toString(),sb,telefone,autorizacaoPiscina.toString(),assinadoPor);
    }
}
