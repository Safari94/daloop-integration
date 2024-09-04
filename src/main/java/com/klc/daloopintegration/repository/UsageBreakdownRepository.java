package com.klc.daloopintegration.repository;

import com.klc.daloopintegration.entities.Hook;
import com.klc.daloopintegration.entities.UsageBreakdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UsageBreakdownRepository extends JpaRepository<UsageBreakdown,Long> {

    List<UsageBreakdown> findByUsageContaining(String usage);

    @Query("SELECT h FROM UsageBreakdown h WHERE h.dateCreated BETWEEN :startOfDay AND :endOfDay ORDER BY h.dateCreated")
    List<UsageBreakdown> findAllRecordsForPreviousDay(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);



    @Query("SELECT h FROM UsageBreakdown h WHERE h.usage = :usage AND h.dateCreated BETWEEN :startDate AND :endDate ORDER BY h.dateCreated")
    List<UsageBreakdown> findUsagesRecordByStationBetweenDates(@Param("usage") String usage, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}



