package com.klc.daloopintegration.services;

import java.io.IOException;

public interface InfraspeakService {

    String sendTicketInfraspeak(String stationId,String description,Integer problemId) throws IOException, InterruptedException;
}
