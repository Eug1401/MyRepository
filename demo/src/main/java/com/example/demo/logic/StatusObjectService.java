package com.example.demo.logic;

import com.example.demo.DTO.*;
import com.example.demo.Entity.StatusObject;
import com.example.demo.feign.DemoClient;
import com.example.demo.mapper.StatusObjectMapper;
import com.example.demo.repository.StatusObjectRepository;
import feign.FeignException;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StatusObjectService {

    private final Logger logger = LoggerFactory.getLogger(StatusObjectService.class);

    private final StatusObjectRepository statusObjectRepository;

    private final StatusObjectMapper statusObjectMapper;

    private final DemoClient demoClient;

    @Autowired
    StatusObjectService(StatusObjectRepository statusObjectRepository, StatusObjectMapper statusObjectMapper, DemoClient demoClient) {
        this.statusObjectRepository = statusObjectRepository;
        this.statusObjectMapper = statusObjectMapper;
        this.demoClient = demoClient;
    }


    @CacheEvict(value = "StatusObjectCache", key = "'all'")
    @Transactional
    public EsitDTO addStatusObject(PostStatusObjectDTO statusObjectDTO) {

        StatusObject SO = statusObjectMapper.toEntity(statusObjectDTO);

        statusObjectRepository.save(SO);

        try {
            demoClient.addElement();
        } catch (FeignException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        return new PositiveEsitDTO("Status object salvato");
    }


    @CacheEvict(value = "StatusObjectCache", key = "'all'")  //pulisce la cache se viene aggiornata la lista, in modo che verrà ricaricata dal db alla prossima get
    @Transactional
    public EsitDTO modifyStatusObject (PutStatusObjectDTO PSO) {

        Optional<StatusObject> statusObject = statusObjectRepository.findById(PSO.getCodiceIdentificativo());
        if(statusObject.isPresent()) {

            StatusObject SO = statusObject.get();

            statusObjectMapper.updateStatusObjectFromPutStatusObjectDTO(PSO, SO);

            statusObjectRepository.save(SO);

            try {
                demoClient.putElement();
            } catch (FeignException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }

            return new PositiveEsitDTO("Stato dell'oggetto: "+SO.getCodiceIdentificativo()+ " aggiornato");
        } else {
            return new NegativeEsitDTO("Risorsa non trovata");
        }
    }

    //viene utilizzata entry chiamata 'all' per salvare in Redis
    //funziona come una tabella hash
    //l'operazione di pulizia in caso di aggiornamento deve essere realizzata sulla stessa entry
    @Cacheable(value = "StatusObjectCache", key = "'all'") //in caso di get successive, salva in cache per velocizzare il recupero
    public List<GetStatusObjectDTO> getAllStatusObject() {
        return statusObjectRepository.findAll().stream()
                .map(statusObjectMapper::toGetStatusObject).toList();  //mappa elementi trovati nel findAll in elementi toGetStatusObject e costruisce una lista
    }


}
