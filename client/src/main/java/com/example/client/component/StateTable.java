package com.example.client.component;

import com.example.client.enums.Stato;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Data
public class StateTable {
    HashMap<Stato, Integer> table = new HashMap<Stato, Integer>();

    public StateTable() {
        for (Stato s : Stato.values()) {
            table.put(s, 0);
        }
    }
}
