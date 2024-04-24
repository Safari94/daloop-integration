package com.klc.daloopintegration.services.impl;


import com.klc.daloopintegration.mappers.ConnectivityMapper;
import com.klc.daloopintegration.model.ConnectivityDTO;
import com.klc.daloopintegration.repository.HookRepository;
import com.klc.daloopintegration.services.ConnectivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConnectivityServiceImpl implements ConnectivityService {

    @Autowired
    private HookRepository hookRepository;

    @Autowired
    private ConnectivityMapper connectivityMapper;

    @Override
    public List<ConnectivityDTO> getAllByStationId(String stationId) {

        return this.hookRepository.findByStationInfo(stationId).stream().map(connectivityMapper::toDTO).toList();

    }
}
