package com.example.client.dto;

import com.example.client.enums.Stato;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetStatusObjectDTO implements Serializable {        //deve essere serializzabile per essere salvato da Redis
    private String nome;
    private Stato stato;
}
