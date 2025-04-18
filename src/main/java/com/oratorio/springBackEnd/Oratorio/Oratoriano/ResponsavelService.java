package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import org.springframework.stereotype.Service;

@Service
public class ResponsavelService {
    private final ResponsavelRepo responsavelRepo;

    public ResponsavelService(ResponsavelRepo responsavelRepo) {
        this.responsavelRepo = responsavelRepo;
    }


}
