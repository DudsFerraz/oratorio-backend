package com.oratorio.springBackEnd.Models.Oratorio.FichaMedica;

import com.oratorio.springBackEnd.Models.Documentos.CPF;
import com.oratorio.springBackEnd.Models.Oratorio.Oratoriano;

class Responsavel {
    private String nome;
    private CPF cpf;
    private String parentesco;
    private final Oratoriano oratoriano;

    public Responsavel(String nome, CPF cpf, String parentesco, Oratoriano oratoriano) {
        this.nome = nome;
        this.cpf = cpf;
        this.parentesco = parentesco;
        this.oratoriano = oratoriano;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public CPF getCpf() {
        return cpf;
    }
    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }
    public String getParentesco() {
        return parentesco;
    }
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    @Override
    public String toString() {
        return String.format("""
                %s (Responsável) | %s de %s
                CPF: %s
                """
                ,nome,parentesco,oratoriano.getNome(),cpf.fullCpf);
    }
}
