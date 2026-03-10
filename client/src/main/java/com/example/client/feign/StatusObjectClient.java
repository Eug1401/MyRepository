package com.example.client.feign;

import com.example.client.dto.GetStatusObjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//nell'url va specificato il nome dell'altro servizio nel container (demo -> nel container è app)
@FeignClient(name="object-status-service", url="http://app:8080")
//feign client -> strategia per poter effettuare chiamate HTTP verso altre applicazioni in modo semplice
//anzichè gestire manualmente gli header, il corpo, il body, la response e la request, si realizza un metodo Java che verrà convertito in chiamata direttamente da Spring
public interface StatusObjectClient {

    //l'interfaccia sarà implementata da Spring che realizzerà tutto dietro le quinte

    @GetMapping("/status-object/getAllStatusObject")
    List<GetStatusObjectDTO> getAllStatusObject();

    //la conversione da Json (in arrivo dopo la chiamata) a DTO sarà realizzata da Spring
}
