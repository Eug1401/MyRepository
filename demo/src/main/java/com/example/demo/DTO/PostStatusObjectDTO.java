package com.example.demo.DTO;

import com.example.demo.Enums.Stato;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PostStatusObjectDTO {
    private String codiceIdentificativo;
    private String nome;
}
