package com.oratorio.springBackEnd.Models.Oratorio.FichaMedica;

class Endereco {
    private String CEP;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;

    public Endereco() {}

    public Endereco(String CEP, String cidade, String rua, String bairro, String numero) {
        this.CEP = CEP;
        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
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
}
