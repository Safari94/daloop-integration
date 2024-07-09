package com.klc.daloopintegration.services;

import java.io.IOException;

public interface InfraspeakService {

    String sendTicketInfraspeak(String stationId) throws IOException, InterruptedException;
}
