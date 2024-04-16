package com.klc.daloopintegration.mappers;


import com.klc.daloopintegration.dto.UsageBreakdownDTO;
import com.klc.daloopintegration.entities.UsageBreakdown;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsageBreakdownMapper {

    UsageBreakdownMapper INSTANCE = Mappers.getMapper(UsageBreakdownMapper.class);



    UsageBreakdownDTO entityToDto(UsageBreakdown entity);

    UsageBreakdown dtoToEntity(UsageBreakdownDTO dto);
}
