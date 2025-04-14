package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import com.oratorio.springBackEnd.Documentos.CPF;
import com.oratorio.springBackEnd.Documentos.RG;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.FichaMedica.FichaMedicaCreationDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.FichaMedica.FichaMedicaResponseDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Responsavel.ResponsavelCreationDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FichaMedicaService {
    private final FichaMedicaRepo fichaMedicaRepo;
    private final ResponsavelRepo responsavelRepo;
    private final ResponsavelOratorianoRepo responsavelOratorianoRepo;
    private final OratorianoRepo oratorianoRepo;

    public FichaMedicaService(FichaMedicaRepo fichaMedicaRepo,ResponsavelRepo responsavelRepo,
                              ResponsavelOratorianoRepo responsavelOratorianoRepo,OratorianoRepo oratorianoRepo) {
        this.fichaMedicaRepo = fichaMedicaRepo;
        this.responsavelRepo = responsavelRepo;
        this.responsavelOratorianoRepo = responsavelOratorianoRepo;
        this.oratorianoRepo = oratorianoRepo;
    }


    @Transactional
    public FichaMedicaResponseDTO setFichaMedica(FichaMedicaCreationDTO dto) {
        Oratoriano oratoriano = oratorianoRepo.findById(dto.oratorianoId())
                .orElseThrow(() -> new IllegalArgumentException("Oratoriano nao encontrado")); //acha o oratoriano
        Endereco endereco = new Endereco(dto.CEP(),dto.cidade(), dto.bairro(), dto.rua(), dto.numero());
        CPF cpf = new CPF(dto.cpf());
        RG rg = new RG(dto.rg());

        Set<ResponsavelOratoriano> responsaveis = new HashSet<>();
        for (ResponsavelCreationDTO dtoRes : dto.responsaveis()){
            CPF cpfRes = new CPF(dtoRes.cpfRes());
            Responsavel res = responsavelRepo.findById(cpfRes).orElse(null);
            if(res==null){
                res = responsavelRepo.save(new Responsavel(dtoRes.nomeRes(),cpfRes)); //cria e salva o novo Responsavel
            }

            ResponsavelOratoriano relacao = responsavelOratorianoRepo.save
                    (new ResponsavelOratoriano(res,oratoriano, dtoRes.parentescoRes())); // cria e salva a relacao

            responsaveis.add(relacao);
        }

        DadosSaude dadosSaude = new DadosSaude(dto.acompanhamentoMedico(),dto.restricaoAtividadeFisica(),
                dto.medicamentos(), dto.alergias(),dto.teveConvulsoes(),dto.temDesmaios(),dto.problemasCardiacos(),
                dto.outrosProblemas(), dto.infoAdicionais());

        AutorizacaoPiscina autorizacaoPiscina = new AutorizacaoPiscina(dto.sabeNadar(), dto.aptoAPiscina(),
                dto.assinadoPor(),dto.date());

        FichaMedica fichaMedica = new FichaMedica(oratoriano,dto.dataDeNascimento(),endereco,cpf,rg,dto.telefone(),
                dto.escola(),dto.serie(),responsaveis,dadosSaude,autorizacaoPiscina);
        fichaMedicaRepo.save(fichaMedica);

        return new FichaMedicaResponseDTO(fichaMedica.getId(),oratoriano.getId());
    }
}
