package com.oratorio.springBackEnd.Oratorio.Dia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DiaRepo extends JpaRepository<Dia, LocalDate> {
}
