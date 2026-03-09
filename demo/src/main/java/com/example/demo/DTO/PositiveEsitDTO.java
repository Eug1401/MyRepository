package com.example.demo.DTO;

import com.example.demo.Enums.Esito;

public class PositiveEsitDTO extends EsitDTO{
    public PositiveEsitDTO(String message) {
        super(Esito.POSITIVO, message);
    }
}
