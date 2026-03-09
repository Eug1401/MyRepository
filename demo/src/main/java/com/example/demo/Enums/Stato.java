package com.example.demo.Enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Stato {
    AGGIORNATO("AGGIORNATO"),
    DA_AGGIORNARE("DA_AGGIORNARE");

    private final String valore;

    Stato(String valore) {
        this.valore = valore;
    }
}
