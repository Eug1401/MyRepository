package com.example.client.session;

import com.example.client.component.StateTable;
import com.example.client.logic.StatusObjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stateTable-controller")
public class Controller {

    private final StatusObjectService statusObjectService;

    public Controller(StatusObjectService statusObjectService) {
        this.statusObjectService = statusObjectService;
    }

    @PostMapping("/add")
    public void addElement() {statusObjectService.addElementInTable(); }

    @PostMapping("/put")
    public void putTable() {statusObjectService.putStateTable(); }

    @GetMapping("/get")
    public StateTable getStateTable() {return statusObjectService.getStateTable(); }
}
