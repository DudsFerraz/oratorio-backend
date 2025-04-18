package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.FichaMedica.FichaMedicaCreationDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.FichaMedica.FichaMedicaResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fichaMedica")
public class FichaMedicaController {

    private final FichaMedicaService fichaMedicaService;

    public FichaMedicaController(FichaMedicaService fichaMedicaService) {
        this.fichaMedicaService = fichaMedicaService;
    }

    @PostMapping
    public FichaMedicaResponseDTO fichaMedica(@RequestBody FichaMedicaCreationDTO dto) {
        return fichaMedicaService.setFichaMedica(dto);
    }
}
