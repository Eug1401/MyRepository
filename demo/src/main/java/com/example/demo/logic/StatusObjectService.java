package com.example.demo.logic;

import com.example.demo.DTO.*;
import com.example.demo.Entity.StatusObject;
import com.example.demo.Enums.Esito;
import com.example.demo.Enums.Stato;
import com.example.demo.mapper.StatusObjectMapper;
import com.example.demo.repository.StatusObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.projection.EntityProjection.ProjectionType.DTO;

@Service
public class StatusObjectService {

    private final StatusObjectRepository statusObjectRepository;

    private final StatusObjectMapper statusObjectMapper;

    @Autowired
    StatusObjectService(StatusObjectRepository statusObjectRepository, StatusObjectMapper statusObjectMapper) {
        this.statusObjectRepository = statusObjectRepository;
        this.statusObjectMapper = statusObjectMapper;
    }


    @CacheEvict(value = "StatusObjectCache", key = "'all'")
    public EsitDTO addStatusObject(PostStatusObjectDTO statusObjectDTO) {

        StatusObject SO = statusObjectMapper.toEntity(statusObjectDTO);

        statusObjectRepository.save(SO);
        return new PositiveEsitDTO("Status object salvato");
    }


    @CacheEvict(value = "StatusObjectCache", key = "'all'")  //pulisce la cache se viene aggiornata la lista, in modo che verrà ricaricata dal db alla prossima get
    public EsitDTO modifyStatusObject (PutStatusObjectDTO PSO) {

        Optional<StatusObject> statusObject = statusObjectRepository.findById(PSO.getCodiceIdentificativo());
        if(statusObject.isPresent()) {

            StatusObject SO = statusObject.get();

            statusObjectMapper.updateStatusObjectFromPutStatusObjectDTO(PSO, SO);

            statusObjectRepository.save(SO);

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
