package com.klc.daloopintegration.services;

import java.io.IOException;

public interface InfraspeakService {

    void postToMaintenanceService(String  stationId, Integer problemId);
}
