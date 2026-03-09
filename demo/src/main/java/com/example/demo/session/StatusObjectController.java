package com.example.demo.session;

import com.example.demo.DTO.EsitDTO;
import com.example.demo.DTO.GetStatusObjectDTO;
import com.example.demo.DTO.PostStatusObjectDTO;
import com.example.demo.DTO.PutStatusObjectDTO;
import com.example.demo.Entity.StatusObject;
import com.example.demo.logic.StatusObjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status-object")
public class StatusObjectController {

    private final StatusObjectService statusObjectService;

    StatusObjectController(StatusObjectService statusObjectService) {
        this.statusObjectService = statusObjectService;
    }

    @PostMapping("/add")
    public EsitDTO addStatusObject(@RequestBody PostStatusObjectDTO statusObject){
        return statusObjectService.addStatusObject(statusObject);
    }

    @PutMapping("/put")
    public EsitDTO putStatusObject(@RequestBody PutStatusObjectDTO statusObjectDTO) {
        return statusObjectService.modifyStatusObject(statusObjectDTO);
    }

    @GetMapping("/getAllStatusObject")
    public List<GetStatusObjectDTO> getAll() {
        return statusObjectService.getAllStatusObject();
    }
}
