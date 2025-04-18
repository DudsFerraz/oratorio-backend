package com.oratorio.springBackEnd.Oratorio.Oratoriano.DTOs.Oratoriano;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record OratorianoCreationDTO(String nome,LocalDate dataNascimento) {
}
