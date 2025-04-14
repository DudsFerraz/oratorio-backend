package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import com.oratorio.springBackEnd.Documentos.CPF;
import com.oratorio.springBackEnd.Documentos.RG;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.FichaMedica.FichaMedicaCreationDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.FichaMedica.FichaMedicaResponseDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Responsavel.ResponsavelCreationDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OratorianoService {

    private final OratorianoRepo oratorianoRepo;
    private final ResponsavelRepo responsavelRepo;

    public OratorianoService(OratorianoRepo oratorianoRepo, ResponsavelRepo responsavelRepo) {
        this.oratorianoRepo = oratorianoRepo;
        this.responsavelRepo = responsavelRepo;
    }



}
