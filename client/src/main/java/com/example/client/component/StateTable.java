package com.example.client.component;

import com.example.client.enums.Stato;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

//Singleton
@Component
@Data
public class StateTable{
    HashMap<Stato, Integer> table = new HashMap<Stato, Integer>();

    public StateTable() {
        for (Stato s : Stato.values()) {
            table.put(s, 0);
        }
    }

    public void addElement() {
        this.table.put(Stato.DA_AGGIORNARE, this.table.get(Stato.DA_AGGIORNARE) +1);
    }


    public void putElement() {
        this.table.put(Stato.AGGIORNATO, this.table.get(Stato.AGGIORNATO) + 1);
        this.table.put(Stato.DA_AGGIORNARE, this.table.get(Stato.DA_AGGIORNARE) - 1);
    }


    public void reset() {
        this.table.replaceAll((k, v) -> 0);
    }
}
