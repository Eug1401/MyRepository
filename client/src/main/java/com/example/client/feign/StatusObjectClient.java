package com.example.client.feign;

import com.example.client.dto.GetStatusObjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="object-status-service", url="http://localhost:8080")
public interface StatusObjectClient {

    @GetMapping("/status-object/getAllStatusObject")
    List<GetStatusObjectDTO> getAllStatusObject();
}
