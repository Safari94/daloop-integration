package com.klc.daloopintegration.repository;

import com.klc.daloopintegration.entities.UsageBreakdown;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsageBreakdownRepository extends JpaRepository<UsageBreakdown,Long> {

    List<UsageBreakdown> findByUsageContaining(String usage);
}
