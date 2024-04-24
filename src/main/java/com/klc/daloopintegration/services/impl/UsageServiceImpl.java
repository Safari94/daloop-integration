package com.klc.daloopintegration.services.impl;

import com.klc.daloopintegration.mappers.UsageBreakdownMapper;
import com.klc.daloopintegration.model.UsageBreakdownDTO;
import com.klc.daloopintegration.repository.UsageBreakdownRepository;
import com.klc.daloopintegration.services.UsageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UsageServiceImpl implements UsageService {

    @Autowired
    private UsageBreakdownRepository usageBreakdownRepository;
   @Autowired
    private UsageBreakdownMapper usageBreakdownMapper;

    @Override
    public List<UsageBreakdownDTO> getAllUsageBreakdownByUsage(String usage) {
        return this.usageBreakdownRepository.findByUsageContaining(usage).stream().map(usageBreakdownMapper::toDTO).toList();
    }
}
