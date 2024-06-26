package com.klc.daloopintegration.services;

import com.klc.daloopintegration.model.UsageBreakdownDTO;

import java.util.List;

public interface UsageService {

    List<UsageBreakdownDTO> getAllUsageBreakdownByUsage(String usage);
    public void inspectTransactionWithLessThan3MinOrMore12Hours();

}
