package com.oratorio.springBackEnd.Documentos;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class RG implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String fullRg;

    public RG(){}
    public RG(String rg) {
        this.fullRg = rg;
    }

    public String getFullRg() {
        return fullRg;
    }
}
