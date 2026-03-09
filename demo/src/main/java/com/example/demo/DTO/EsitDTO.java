package com.example.demo.DTO;

import com.example.demo.Enums.Esito;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data  //lombock crea costruttore, getter() e setter()
@AllArgsConstructor
public class EsitDTO {

    private Esito esito;

    private String Message;

}
