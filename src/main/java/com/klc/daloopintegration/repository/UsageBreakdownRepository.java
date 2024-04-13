package com.klc.daloopintegration.repository;

import com.klc.daloopintegration.entities.UsageBreakdown;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageBreakdownRepository extends JpaRepository<UsageBreakdown,Long> {
}
