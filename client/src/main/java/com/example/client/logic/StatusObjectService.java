package com.example.client.logic;

import com.example.client.component.StateTable;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class StatusObjectService {

    private final StateTable stateTable;

    public StatusObjectService(StateTable stateTable) {
        this.stateTable = stateTable;
    }

    public void addElementInTable() {
        stateTable.addElement();
    }

    public void putStateTable() {
        stateTable.putElement();
    }
}
