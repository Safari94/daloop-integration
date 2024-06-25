package com.klc.daloopintegration.repository;

import com.klc.daloopintegration.entities.Hook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;



public interface HookRepository extends JpaRepository<Hook, UUID> {

    List<Hook> findByStationInfo(String stationInfo);


    @Query("SELECT h FROM Hook h WHERE h.createdDate BETWEEN :startOfDay AND :endOfDay ORDER BY h.createdDate")
    List<Hook> findAllRecordsForPreviousDay(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
