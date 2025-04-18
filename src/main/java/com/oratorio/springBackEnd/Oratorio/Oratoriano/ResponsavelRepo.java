package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import com.oratorio.springBackEnd.Documentos.CPF;
import com.oratorio.springBackEnd.Oratorio.Oratoriano.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepo extends JpaRepository<Responsavel, CPF> {
}
