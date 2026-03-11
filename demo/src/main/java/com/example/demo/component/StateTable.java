package com.example.demo.component;

import com.example.demo.Enums.Stato;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Data
public class StateTable {
    HashMap<Stato, Integer> table = new HashMap<Stato, Integer>();

    public StateTable() {
        for (Stato s : Stato.values()) {
            table.put(s, 0);
        }
    }


    public void reset() {
        this.table.replaceAll((k, v) -> 0);
    }
}
