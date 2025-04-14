package com.oratorio.springBackEnd.Oratorio.Oratoriano;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
class AutorizacaoPiscina implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private boolean sabeNadar;

    @Column(nullable = false)
    private boolean podePiscina;

    @Column(nullable = false)
    private String assinadoPor;

    @Column(nullable = false)
    private LocalDate data;


    public AutorizacaoPiscina() {}
    public AutorizacaoPiscina(boolean sabeNadar, boolean aptoAPiscina, String assinadoPor, LocalDate date) {
        this.sabeNadar = sabeNadar;
        this.podePiscina = aptoAPiscina;
        this.assinadoPor = assinadoPor;
        this.data = date;
    }

    @Override
    public String toString() {
        String sabeNadar;
        if(this.sabeNadar) sabeNadar = "SIM"; else sabeNadar = "NÃO";

        String aptoAPiscina;
        if(this.podePiscina) aptoAPiscina = "SIM"; else aptoAPiscina = "NÃO";

        return String.format("Sabe nadar: %s | Pode entrar na piscina: %s", sabeNadar, aptoAPiscina);
    }
    public boolean sabeNadar() {
        return sabeNadar;
    }
    public boolean podePiscina() {
        return podePiscina;
    }
    public String getAssinadoPor() {
        return assinadoPor;
    }
    public LocalDate getData() {
        return data;
    }
}
