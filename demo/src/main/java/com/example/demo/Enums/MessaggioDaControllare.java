package com.example.demo.Enums;

import lombok.Getter;

@Getter
public enum MessaggioDaControllare {
    CIAO("ciao"),
    PROVA("prova"),
    TEST("test");

    private final String valore;

    MessaggioDaControllare (String valore) {
        this.valore = valore;
    }
}
