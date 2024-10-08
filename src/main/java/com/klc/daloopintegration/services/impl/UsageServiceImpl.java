package com.klc.daloopintegration.services.impl;

import com.klc.daloopintegration.entities.UsageBreakdown;
import com.klc.daloopintegration.mappers.UsageBreakdownMapper;
import com.klc.daloopintegration.model.UsageBreakdownDTO;
import com.klc.daloopintegration.repository.UsageBreakdownRepository;
import com.klc.daloopintegration.services.InfraspeakService;
import com.klc.daloopintegration.services.UsageService;
import com.klc.daloopintegration.utils.ProblemsDescription;
import com.klc.daloopintegration.utils.ProblemsIds;
import com.klc.daloopintegration.utils.StringManipulation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class UsageServiceImpl implements UsageService {

    @Autowired
    private UsageBreakdownRepository usageBreakdownRepository;
   @Autowired
    private UsageBreakdownMapper usageBreakdownMapper;
   @Autowired
   private InfraspeakService infraspeakService;

    @Override
    public List<UsageBreakdownDTO> getAllUsageBreakdownByUsage(String usage) {
        return this.usageBreakdownRepository.findByUsageContaining(usage).stream().map(usageBreakdownMapper::toDTO).toList();
    }
    @Override
    public void inspectTransactionWithLessThan3MinOrMore12Hours(){
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDateTime startOfDay = yesterday.atStartOfDay();
        LocalDateTime endOfDay = yesterday.atTime(LocalTime.MAX);

        List<UsageBreakdown> usageBreakdownList = this.usageBreakdownRepository.findAllRecordsForPreviousDay(startOfDay,endOfDay);

        for(UsageBreakdown u:usageBreakdownList){
            if(u.getTotalDuration()<=120){
                String stationName = StringManipulation.removeLastNumberAfterHyphen(u.getUsage());
                try{
                    this.infraspeakService.postToMaintenanceService(StringManipulation.getStationWithoutConnect(stationName),  ProblemsIds.TWO_MIN_SESSION_ID);}
                catch (Exception ex){
                    log.error(ex.toString());
                }

                log.info("Station {} has a session {} with total duration {}",stationName, u.getId(),u.getTotalDuration());
            }

            if(u.getTotalDuration()>=43200 ){
                String stationName = StringManipulation.removeLastNumberAfterHyphen(u.getUsage());
                try{
                    this.infraspeakService.postToMaintenanceService(StringManipulation.getStationWithoutConnect(stationName),  ProblemsIds.TWELVE_HR_SESSION_ID);}
                catch (Exception ex){
                    log.error(ex.toString());
                }

                log.info("Station {} has a session {} with total duration {}",stationName, u.getId(),u.getTotalDuration());
            }
        }
    }

    @Override
    public List<UsageBreakdownDTO> getAllUsageOfaStationBetweenDates(String usage, LocalDateTime startDate, LocalDateTime endDate) {

        return this.usageBreakdownRepository.findUsagesRecordByStationBetweenDates(usage,startDate,endDate).stream().map(usageBreakdownMapper::toDTO).toList();
    }
}


