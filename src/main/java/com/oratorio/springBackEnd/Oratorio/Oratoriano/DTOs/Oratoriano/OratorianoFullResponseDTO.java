package com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Oratoriano;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oratorio.springBackEnd.Documentos.CPF;
import com.oratorio.springBackEnd.Documentos.RG;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Endereco.EnderecoFullResponseDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Responsavel.ResponsavelOratorianoDTO;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Responsavel.ResponsavelShortResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record OratorianoFullResponseDTO(String nome, long id, @JsonFormat(pattern ="dd/MM/yyyy")LocalDate dataNascimento,
                                        String cpf, String rg, EnderecoFullResponseDTO enderecoDto, String telefone,
                                        String escola, String anoEscola, List<ResponsavelOratorianoDTO> responsaveisDto,
                                        long fichaMedicaId, int presencasTotais) {
}
