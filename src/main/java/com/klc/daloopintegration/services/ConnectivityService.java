package com.klc.daloopintegration.services;

import com.klc.daloopintegration.model.ConnectivityDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ConnectivityService {

    List<ConnectivityDTO> getAllByStationId(String stationId);

    List<ConnectivityDTO> getAllByStationIdBetweenDates(String stationId, LocalDateTime startDate, LocalDateTime endDate);

    public void validateStationsIssues();


}
