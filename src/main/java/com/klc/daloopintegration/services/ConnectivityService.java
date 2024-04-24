package com.klc.daloopintegration.services;

import com.klc.daloopintegration.model.ConnectivityDTO;

import java.util.List;

public interface ConnectivityService {

    List<ConnectivityDTO> getAllByStationId(String stationId);


}
