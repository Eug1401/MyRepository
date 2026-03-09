package com.example.demo.Enums;

import lombok.Getter;

@Getter
public enum Esito {

    POSITIVO("POSITIVO"),
    NEGATIVO("NEGATIVO");

    private final String valore;

    Esito(String valore) {
        this.valore = valore;
    }

}
