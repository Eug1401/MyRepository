package com.example.client.session;

import com.example.client.enums.Stato;
import com.example.client.logic.StatusObjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/state-controller")
public class Controller {

    private final StatusObjectService statusObjectService;

    public Controller(StatusObjectService statusObjectService) {
        this.statusObjectService = statusObjectService;
    }

    @GetMapping("/count")
    public HashMap<Stato, Integer> countStates() {
        return statusObjectService.countState();
    }
}
