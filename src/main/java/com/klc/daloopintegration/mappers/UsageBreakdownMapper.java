package com.klc.daloopintegration.mappers;


import com.klc.daloopintegration.dto.UsageBreakdownDTO;
import com.klc.daloopintegration.entities.UsageBreakdown;
import org.mapstruct.Mapper;

@Mapper
public interface UsageBreakdownMapper {

    UsageBreakdownDTO entityToDto(UsageBreakdown entity);

    UsageBreakdown dtoToEntity(UsageBreakdownDTO dto);
}
