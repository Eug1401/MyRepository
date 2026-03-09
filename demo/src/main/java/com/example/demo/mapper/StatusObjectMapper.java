package com.example.demo.mapper;

import com.example.demo.DTO.GetStatusObjectDTO;
import com.example.demo.DTO.ResponseStatusObjectDTO;
import com.example.demo.DTO.PostStatusObjectDTO;
import com.example.demo.DTO.PutStatusObjectDTO;
import com.example.demo.Entity.StatusObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StatusObjectMapper {

    PostStatusObjectDTO toPostStatusObjectDTO(StatusObject statusObject);

    PutStatusObjectDTO toPutStatusObjectDTO(StatusObject statusObject);

    @Mapping(target = "stato", constant = "DA_AGGIORNARE")
    StatusObject toEntity(PostStatusObjectDTO postStatusObjectDTO);

    ResponseStatusObjectDTO toResponseStatusObject(StatusObject statusObject);

    GetStatusObjectDTO toGetStatusObject(StatusObject statusObject);

    void updateStatusObjectFromPutStatusObjectDTO(
            PutStatusObjectDTO dto,
            @MappingTarget StatusObject statusObject
    );
}

