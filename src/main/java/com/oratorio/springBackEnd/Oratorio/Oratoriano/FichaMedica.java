package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import com.oratorio.springBackEnd.Documentos.CPF;
import com.oratorio.springBackEnd.Documentos.RG;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
class FichaMedica implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "fichaMedica")
    private Oratoriano oratoriano;

    @Embedded
    private AutorizacaoPiscina autorizacaoPiscina;

    @Embedded
    private DadosSaude dadosSaude;

    public FichaMedica() {}
    public FichaMedica(Oratoriano oratoriano, LocalDate dataDeNascimento, Endereco endereco, CPF cpf, RG rg,
                       String telefone, String escola, String serie, Set<ResponsavelOratoriano> res, DadosSaude dadosSaude,
                       AutorizacaoPiscina autorizacaoPiscina) {

        this.oratoriano = oratoriano;
        oratoriano.setFichaMedica(this);
        oratoriano.setDataDeNascimento(dataDeNascimento);
        oratoriano.setEndereco(endereco);
        oratoriano.setCpf(cpf);
        oratoriano.setRg(rg);
        oratoriano.setTelefone(telefone);
        oratoriano.setEscola(escola);
        oratoriano.setAnoEscola(serie);
        oratoriano.setResponsaveis(res);

        this.dadosSaude = dadosSaude;
        this.autorizacaoPiscina = autorizacaoPiscina;
    }



    public long getId() {
        return id;
    }
    public Oratoriano getOratoriano() {
        return oratoriano;
    }
    public DadosSaude getDadosSaude() {
        return dadosSaude;
    }
    public AutorizacaoPiscina getAutorizacaoPiscina() {
        return autorizacaoPiscina;
    }
    public boolean podePiscina() {
        return autorizacaoPiscina.podePiscina();
    }

    @Override
    public String toString() {
        return String.format("""
                %s (Oratoriano) | ID: %s
                
                %s
                
                %s
                
                Assinado por: %s
                """,
                oratoriano.getNome(),oratoriano.getId(),getAutorizacaoPiscina().toString(),getDadosSaude().toString(),
                getAutorizacaoPiscina().getAssinadoPor());
    }
}
