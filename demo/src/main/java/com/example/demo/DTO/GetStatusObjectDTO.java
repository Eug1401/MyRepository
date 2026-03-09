package com.example.demo.DTO;

import com.example.demo.Enums.Stato;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetStatusObjectDTO implements Serializable {        //deve essere serializzabile per essere salvato da Redis
    private String nome;
    private Stato stato;
}
