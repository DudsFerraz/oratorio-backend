package com.oratorio.springBackEnd.Oratorio.AnoDeOratorio;

import com.oratorio.springBackEnd.Oratorio.AnoDeOratorio.DTOs.AnoDeOratorioCreationDTO;
import com.oratorio.springBackEnd.Oratorio.AnoDeOratorio.DTOs.AnoDeOratorioResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AnoDeOratorio")
public class AnoDeOratorioController {

    private final AnoDeOratorioService anoDeOratorioService;

    public AnoDeOratorioController(AnoDeOratorioService anoDeOratorioService) {
        this.anoDeOratorioService = anoDeOratorioService;
    }

    @PostMapping
    public AnoDeOratorioResponseDTO create(@RequestBody AnoDeOratorioCreationDTO dto){
       return anoDeOratorioService.create(dto);
    }

    @GetMapping
    public List<AnoDeOratorioResponseDTO> getAll(){
        return anoDeOratorioService.getAll();
    }

}
