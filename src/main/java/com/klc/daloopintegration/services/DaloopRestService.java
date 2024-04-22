package com.klc.daloopintegration.services;

import com.klc.daloopintegration.data.HookData;
import com.klc.daloopintegration.model.UsageBreakdownDTO;

import java.util.UUID;

public interface DaloopRestService {

    UsageBreakdownDTO getTransactionsDetails(String transactionId);

    void registerConnectivityEvent(HookData hookTemplate);

   UUID storeStartTransaction(HookData hookTemplate);

    void endTransaction(HookData hookTemplate);
}
