package com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.FichaMedica;

import com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Responsavel.ResponsavelCreationDTO;

import java.time.LocalDate;
import java.util.List;

public record FichaMedicaCreationDTO(long oratorianoId, LocalDate dataDeNascimento, String CEP, String cidade,
                                     String rua, String bairro, String numero, String cpf, String rg, String telefone,
                                     String escola, String serie, List<ResponsavelCreationDTO> responsaveis,
                                     String acompanhamentoMedico, String restricaoAtividadeFisica, String medicamentos,
                                     String alergias, boolean teveConvulsoes, boolean temDesmaios, String problemasCardiacos,
                                     String outrosProblemas, String infoAdicionais, boolean sabeNadar, boolean aptoAPiscina,
                                    String assinadoPor, LocalDate date) {
}
