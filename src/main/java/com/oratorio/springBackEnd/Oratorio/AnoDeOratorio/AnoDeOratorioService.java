package com.oratorio.springBackEnd.Oratorio.AnoDeOratorio;

import com.oratorio.springBackEnd.Oratorio.AnoDeOratorio.DTOs.AnoDeOratorioCreationDTO;
import com.oratorio.springBackEnd.Oratorio.AnoDeOratorio.DTOs.AnoDeOratorioResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AnoDeOratorioService {

    private final AnoDeOratorioRepo anoDeOratorioRepo;

    public AnoDeOratorioService(AnoDeOratorioRepo anoDeOratorioRepo) {
        this.anoDeOratorioRepo = anoDeOratorioRepo;
    }

    private AnoDeOratorioResponseDTO generateResponseDTO(AnoDeOratorio anoDeOratorio) {
        return new AnoDeOratorioResponseDTO(anoDeOratorio.getNome(), anoDeOratorio.getId());
    }

    public AnoDeOratorioResponseDTO create(AnoDeOratorioCreationDTO dto) {
        AnoDeOratorio newAnoDeOratorio = anoDeOratorioRepo.save(new AnoDeOratorio(dto.nome(),dto.dataInicio(),dto.dataFim()));
        return generateResponseDTO(newAnoDeOratorio);
    }

    public List<AnoDeOratorioResponseDTO> getAll(){
        List<AnoDeOratorio> anos = anoDeOratorioRepo.findAll();
        List<AnoDeOratorioResponseDTO> dtos = new ArrayList<>();

        for (AnoDeOratorio ano : anos) {
            dtos.add(generateResponseDTO(ano));
        }

        return dtos;
    }


}
