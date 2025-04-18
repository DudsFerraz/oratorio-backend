package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import com.oratorio.springBackEnd.Documentos.CPF;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Responsavel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CPF cpf;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "responsavel")
    private Set<ResponsavelOratoriano> oratorianos;


    public Responsavel() {}
    public Responsavel(String nome, CPF cpf) {
        this.nome = nome;
        this.cpf = cpf;
        oratorianos = new HashSet<>();
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
    public List<ResponsavelOratoriano> responsavelPor(){
        return new ArrayList<>(oratorianos);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<ResponsavelOratoriano> oratorianos = responsavelPor();
        for (ResponsavelOratoriano o : oratorianos) {
            Oratoriano oratoriano = o.getOratoriano();
            sb.append(oratoriano.getNome());
            sb.append(" | ID: ");
            sb.append(oratoriano.getId());
            sb.append(" (");
            sb.append(o.getParentesco());
            sb.append(")\n");
        }


        return String.format("""
                %s (Responsável) | CPF: %s
                
                Responsável por:
                %s
                """
                ,nome,cpf.getCpf(),sb);
    }
}
