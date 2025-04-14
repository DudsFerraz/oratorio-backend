package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class Endereco implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String CEP;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;

    public Endereco() {}

    public Endereco(String CEP, String cidade, String bairro, String rua, String numero) {
        this.CEP = CEP;
        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
    }


    public String getCEP() {
        return CEP;
    }
    public void setCEP(String CEP) {
        if(CEP==null) throw new IllegalArgumentException("CEP Nulo");
        this.CEP = CEP;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        if(cidade==null) throw new IllegalArgumentException("Cidade Nula");
        this.cidade = cidade;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        if(rua==null) throw new IllegalArgumentException("Rua Nula");
        this.rua = rua;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        if(bairro==null) throw new IllegalArgumentException("Bairro Nulo");
        this.bairro = bairro;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        if(numero==null) throw new IllegalArgumentException("Numero Nulo");
        this.numero = numero;
    }


    @Override
    public String toString() {
        return String.format("""
                CEP: %s | Cidade: %s
                Bairro: %s | Rua: %s | Número: %s
                """,
                CEP, this.cidade, this.bairro, this.rua, this.numero);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(getCEP(), endereco.getCEP()) && Objects.equals(getCidade(), endereco.getCidade()) && Objects.equals(getBairro(), endereco.getBairro()) && Objects.equals(getRua(), endereco.getRua()) && Objects.equals(getNumero(), endereco.getNumero());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCEP(), getCidade(), getBairro(), getRua(), getNumero());
    }
}
