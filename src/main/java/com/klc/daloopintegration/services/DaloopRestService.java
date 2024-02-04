package com.klc.daloopintegration.services;

import com.klc.daloopintegration.data.HookData;

public interface DaloopRestService {

    String getTransactionsDetails(String transactionId);

    void registerConnectivityEvent(HookData hookTemplate);
}
