package com.example.demo.session;

import com.example.demo.DTO.PostIncomingMessageDTO;
import com.example.demo.Entity.IncomingMessage;
import com.example.demo.DTO.EsitDTO;
import com.example.demo.logic.CheckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class DemoController {

    private final CheckService checkService;

    public DemoController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping("/ciao")
    public String returnCiao() { return "ciao"; };


    @PostMapping("/check")
    public EsitDTO checkRequest(@RequestBody PostIncomingMessageDTO requestObject) {
        return checkService.checkMessage(requestObject);
    }

    @PostMapping("/add")
    public EsitDTO saveMessage(@RequestBody PostIncomingMessageDTO requestObject) {
        return checkService.addMessage(requestObject);
    }

    //API in minuscolo separate da trattino in caso -

    //non è necessario mettere nel path il metodo (DELETE) in caso di CRUD controller
    @DeleteMapping("/{id}")
    public EsitDTO deleteElement(@PathVariable Long id) {
        return checkService.deleteById(id);
    }
}
