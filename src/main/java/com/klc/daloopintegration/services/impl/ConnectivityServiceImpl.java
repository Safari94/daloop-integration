package com.klc.daloopintegration.services.impl;


import com.klc.daloopintegration.entities.Hook;
import com.klc.daloopintegration.mappers.ConnectivityMapper;
import com.klc.daloopintegration.model.ConnectivityDTO;
import com.klc.daloopintegration.repository.HookRepository;
import com.klc.daloopintegration.services.ConnectivityService;
import com.klc.daloopintegration.services.InfraspeakService;
import com.klc.daloopintegration.utils.ProblemsDescription;
import com.klc.daloopintegration.utils.ProblemsIds;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@Slf4j
public class ConnectivityServiceImpl implements ConnectivityService {

    @Autowired
    private HookRepository hookRepository;

    @Autowired
    private ConnectivityMapper connectivityMapper;

    @Autowired
    private InfraspeakService infraspeakService;


    @Override
    public List<ConnectivityDTO> getAllByStationId(String stationId) {

        return this.hookRepository.findByStationInfo(stationId).stream().map(connectivityMapper::toDTO).toList();

    }


    @Override
    public List<ConnectivityDTO> getAllByStationIdBetweenDates(String stationId, LocalDateTime startDate, LocalDateTime endDate) {

     return this.hookRepository.findAllConnectivityPerStationBetweenDates(stationId,startDate,endDate).stream().map(connectivityMapper::toDTO).toList();
    }

    @Override
    public void validateStationsIssues() {

        HashMap<String, List<ConnectivityDTO>> result = loadValues();

        Runnable job1 = () -> {
                inspectMoreThan12HoursOffline(result);
        };

        Runnable job2 = () -> {
            inspectAllStationsWithMoreThan6Offline(result);
        };

        Thread thread1 = new Thread(job1);
        Thread thread2 = new Thread(job2);

        thread1.start();
        thread2.start();

        // Wait for both threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        log.info("Both jobs have finished.");
    }





    private HashMap<String,List<ConnectivityDTO>> loadValues(){

        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDateTime startOfDay = yesterday.atStartOfDay();
        LocalDateTime endOfDay = yesterday.atTime(LocalTime.MAX);

        List<Hook> connectivityList = this.hookRepository.findAllRecordsForPreviousDay(startOfDay,endOfDay);
        HashMap<String,List<ConnectivityDTO>> result = new HashMap<>();

        for(Hook k : connectivityList){

            if(result.containsKey(k.getStationInfo())){
                result.get(k.getStationInfo()).add(connectivityMapper.toDTO(k));
            }else{
                List<ConnectivityDTO> cc = new ArrayList<>();
                cc.add(connectivityMapper.toDTO(k));
                result.put(k.getStationInfo(),cc);
            }
        }

        return result;

    }

    private void inspectMoreThan12HoursOffline(HashMap<String, List<ConnectivityDTO>> result){

        for (Map.Entry<String, List<ConnectivityDTO>> entry : result.entrySet()) {

            List<ConnectivityDTO> connectivityDTOS = entry.getValue();
            String previousStatus = "null";
            LocalDateTime lastCreatedTime = LocalDateTime.now();

            for (ConnectivityDTO c : connectivityDTOS) {

                if (previousStatus.equals("null")) {
                    previousStatus = c.getConnectivity();
                    lastCreatedTime = c.getCreatedDate();

                } else {
                    if (c.getConnectivity().equals("online") && previousStatus.equals("offline")) {
                        previousStatus = c.getConnectivity();
                        long duration = getDifferenceInSeconds(lastCreatedTime, c.getCreatedDate());
                        if (duration > 43200) {
                            log.info("Station {} offline more than 12 hours", entry.getKey());
                            break;
                        }

                    } else {
                        previousStatus = c.getConnectivity();
                        lastCreatedTime = c.getCreatedDate();
                    }
                }

            }
        }

    }


    private void inspectAllStationsWithMoreThan6Offline(HashMap<String, List<ConnectivityDTO>> result)  {

        for (Map.Entry<String, List<ConnectivityDTO>> entry : result.entrySet()) {

            int countOffline = 0;

            List<ConnectivityDTO> connectivityDTOS = entry.getValue();

            for (ConnectivityDTO c : connectivityDTOS) {
                if(c.getConnectivity().equals("offline")){
                    countOffline+=1;
                }
                if(countOffline>8){
                    log.info("Station {} sent to much offline register in one day", entry.getKey());
                    try {
                        infraspeakService.postToMaintenanceService(entry.getKey(), ProblemsIds.MANY_OFFLINES_ID);
                    }catch (Exception ex){
                        log.error(ex.toString());
                    }

                    break;
                }
            }


        }



    }

    private static long getDifferenceInSeconds(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        return duration.getSeconds();
    }
}
