package com.oratorio.springBackEnd.Models.Oratorio.FichaMedica;


import com.oratorio.springBackEnd.Models.Documentos.CPF;

import java.time.LocalDate;

class AtestadoMedico {
    private String nome;
    private LocalDate data;
    private CPF cpf;
    private Endereco endereco;

    public AtestadoMedico(String nome, LocalDate data, CPF cpf, Endereco endereco) {
        this.nome = nome;
        this.data = data;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public CPF getCpf() {
        return cpf;
    }

    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
