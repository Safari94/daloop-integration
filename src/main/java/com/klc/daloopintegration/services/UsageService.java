package com.klc.daloopintegration.services;

import com.klc.daloopintegration.model.UsageBreakdownDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface UsageService {

    List<UsageBreakdownDTO> getAllUsageBreakdownByUsage(String usage);

    List<UsageBreakdownDTO> getAllUsageOfaStationBetweenDates(String usage, LocalDateTime startDate, LocalDateTime endDate);
    public void inspectTransactionWithLessThan3MinOrMore12Hours();

}
