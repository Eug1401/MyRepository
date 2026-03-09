package com.example.demo.DTO;

import com.example.demo.Enums.Stato;
import lombok.Data;

@Data
public class PutStatusObjectDTO {
    Stato stato;
    String codiceIdentificativo;
}
