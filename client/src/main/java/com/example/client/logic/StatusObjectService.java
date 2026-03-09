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

    public HashMap<Stato, Integer> countState () {

        stateTable.reset();

        for (GetStatusObjectDTO x : statusObjectClient.getAllStatusObject()) {
            Stato s = x.getStato();
            stateTable.getTable().put(s, stateTable.getTable().get(s) + 1);
        }

        return stateTable.getTable();
    }
}
