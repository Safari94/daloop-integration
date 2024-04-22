package com.klc.daloopintegration.mappers;


import com.klc.daloopintegration.model.UsageBreakdownDTO;
import com.klc.daloopintegration.entities.UsageBreakdown;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsageBreakdownMapper {



    UsageBreakdownDTO toDTO(UsageBreakdown entity);

    UsageBreakdown toEntity(UsageBreakdownDTO dto);
}
