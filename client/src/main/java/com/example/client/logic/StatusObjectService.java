package com.example.client.logic;

import com.example.client.dto.GetStatusObjectDTO;
import com.example.client.enums.Stato;
import com.example.client.feign.StatusObjectClient;
import com.example.client.component.StateTable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StatusObjectService {

    private final StatusObjectClient statusObjectClient;

    private final StateTable stateTable;

    public StatusObjectService(StatusObjectClient statusObjectClient, StateTable stateTable) {
        this.statusObjectClient = statusObjectClient;
        this.stateTable = stateTable;
    }

    //calcolo statistiche su mappa ricevuta
    public HashMap<Stato, Integer> countState () {

        stateTable.reset();  //azzero

        //conteggio degli elementi per ogni stato (AGGIORNATO, DA AGGIORNARE)
        for (GetStatusObjectDTO x : statusObjectClient.getAllStatusObject()) {
            Stato s = x.getStato();
            stateTable.getTable().put(s, stateTable.getTable().get(s) + 1);
        }

        //restituisco mappa che associa ad ogni stato il numero di elementi (in quel particolare stato)
        //prova commit
        return stateTable.getTable();
    }
}
