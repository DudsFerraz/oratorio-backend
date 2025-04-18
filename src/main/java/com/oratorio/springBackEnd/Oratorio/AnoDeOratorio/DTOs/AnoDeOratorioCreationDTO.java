package com.oratorio.springBackEnd.Oratorio.AnoDeOratorio.DTOs;

import java.time.LocalDate;

public record AnoDeOratorioCreationDTO(String nome, LocalDate dataInicio, LocalDate dataFim) {
}
