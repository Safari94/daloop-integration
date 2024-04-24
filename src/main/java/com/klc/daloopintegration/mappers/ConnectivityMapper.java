package com.klc.daloopintegration.mappers;


import com.klc.daloopintegration.entities.Hook;
import com.klc.daloopintegration.model.ConnectivityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConnectivityMapper {

    Hook toEntity(ConnectivityDTO connectivityDTO);

    ConnectivityDTO toDTO(Hook hook);
}
