package com.example.demo.Entity;

import com.example.demo.Enums.Stato;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StatusObject {
    @Id
    private String codiceIdentificativo;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Stato stato;

}
