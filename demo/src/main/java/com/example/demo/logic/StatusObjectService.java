package com.example.demo.logic;

import com.example.demo.DTO.EsitDTO;
import com.example.demo.DTO.GetStatusObjectDTO;
import com.example.demo.DTO.PostStatusObjectDTO;
import com.example.demo.DTO.PutStatusObjectDTO;
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

        EsitDTO res = new EsitDTO();

        try {
            StatusObject SO = statusObjectMapper.toEntity(statusObjectDTO);

            statusObjectRepository.save(SO);
            res.setEsito(Esito.POSITIVO.getValore());
            res.setMessage(SO.getNome());

        } catch(Exception e) {
            res.setEsito(Esito.NEGATIVO.getValore());
            res.setMessage(statusObjectDTO.getNome());
        }

        return res;
    }


    @CacheEvict(value = "StatusObjectCache", key = "'all'")  //pulisce la cache se viene aggiornata la lista, in modo che verrà ricaricata dal db alla prossima get
    public EsitDTO modifyStatusObject (PutStatusObjectDTO PSO) {
        EsitDTO res = new EsitDTO();

        Optional<StatusObject> statusObject = statusObjectRepository.findById(PSO.getCodiceIdentificativo());
        if(statusObject.isPresent()) {

            StatusObject SO = statusObject.get();

            statusObjectMapper.updateStatusObjectFromPutStatusObjectDTO(PSO, SO);

            statusObjectRepository.save(SO);

            res.setMessage("Aggiornamento stato avvenuto");
            res.setEsito(Esito.POSITIVO.getValore());
        } else {
            res.setMessage("Aggiornamento fallito, risorsa non trovata");
            res.setEsito(Esito.NEGATIVO.getValore());
        }

        return res;
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
