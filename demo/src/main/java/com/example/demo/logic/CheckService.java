package com.example.demo.logic;

import com.example.demo.DTO.PostIncomingMessageDTO;
import com.example.demo.Enums.Esito;
import com.example.demo.Entity.IncomingMessage;
import com.example.demo.DTO.EsitDTO;
import com.example.demo.Enums.MessaggioDaControllare;
import com.example.demo.mapper.IncomingMessageMapper;
import com.example.demo.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckService {

    private final ObjectRepository objectRepository;

    private final IncomingMessageMapper incomingMessageMapper;

    @Autowired
    public CheckService(ObjectRepository objectRepository, IncomingMessageMapper incomingMessageMapper) {
        this.objectRepository = objectRepository;
        this.incomingMessageMapper = incomingMessageMapper;
    }

    public EsitDTO checkMessage(PostIncomingMessageDTO IR)
    {
        EsitDTO Res = new EsitDTO();

        IncomingMessage IM = incomingMessageMapper.toIncomingMessage(IR);

        if(IM.getStringaDaControllare().equals(MessaggioDaControllare.CIAO.getValore())) Res.setEsito(Esito.POSITIVO.getValore());

        else Res.setEsito(Esito.NEGATIVO.getValore());

        Res.setMessage(IM.getStringaDaControllare());

        return Res;
    }


    public EsitDTO addMessage(PostIncomingMessageDTO postIncomingMessageDTO) {
        EsitDTO res = new EsitDTO();

        IncomingMessage IM = incomingMessageMapper.toIncomingMessage(postIncomingMessageDTO);
        try {
            objectRepository.save(IM);
            res.setEsito(Esito.POSITIVO.getValore());
        } catch (Exception e) {
            res.setEsito(Esito.NEGATIVO.getValore());
        }

        res.setMessage(IM.getStringaDaControllare());
        return res;
    }


    public EsitDTO deleteById (Long id) {

        EsitDTO RO = new EsitDTO();

        Optional<IncomingMessage> MDC = objectRepository.findById(id);

        if(MDC.isPresent()) {
            RO.setMessage(MDC.get().getStringaDaControllare());

            objectRepository.deleteById(id);
            RO.setEsito(Esito.POSITIVO.getValore());
        }

        else {
            RO.setEsito(Esito.NEGATIVO.getValore());
            RO.setMessage("ID non presente nel database");
        }


        return RO;
    }

}
