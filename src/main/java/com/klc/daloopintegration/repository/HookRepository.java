package com.klc.daloopintegration.repository;

import com.klc.daloopintegration.entities.Hook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;



public interface HookRepository extends JpaRepository<Hook, UUID> {

    List<Hook> findByStationInfo(String stationInfo);
}
