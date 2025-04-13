package com.oratorio.springBackEnd.Models.Oratorio.FichaMedica;

import java.time.LocalDate;

class AutorizacaoPiscina {
    private boolean sabeNadar;
    private boolean podePiscina;
    private Responsavel assinadoPor;
    private LocalDate data;


    public AutorizacaoPiscina() {}
    public AutorizacaoPiscina(boolean sabeNadar, boolean aptoAPiscina, Responsavel assinadoPor, LocalDate data) {
        this.sabeNadar = sabeNadar;
        this.podePiscina = aptoAPiscina;
        this.assinadoPor = assinadoPor;
        this.data = data;
    }

    @Override
    public String toString() {
        String sabeNadar;
        if(this.sabeNadar) sabeNadar = "SIM"; else sabeNadar = "NÃO";

        String aptoAPiscina;
        if(this.podePiscina) aptoAPiscina = "SIM"; else aptoAPiscina = "NÃO";

        return String.format("Sabe nadar: %s | Pode entrar: %s", sabeNadar, aptoAPiscina);
    }
    public boolean sabeNadar() {
        return sabeNadar;
    }
    public void setSabeNadar(boolean sabeNadar) {
        this.sabeNadar = sabeNadar;
    }
    public boolean podePiscina() {
        return podePiscina;
    }

    public Responsavel getAssinadoPor() {
        return assinadoPor;
    }

    public void setAssinadoPor(Responsavel assinadoPor) {
        this.assinadoPor = assinadoPor;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setAptoAPiscina(boolean aptoAPiscina) {
        this.podePiscina = aptoAPiscina;
    }
}
