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

    @Query("SELECT h FROM UsageBreakDown h WHERE h.createdDate BETWEEN :startOfDay AND :endOfDay ORDER BY h.createdDate")
    List<UsageBreakdown> findAllRecordsForPreviousDay(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

}



