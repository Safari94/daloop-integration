package com.klc.daloopintegration.repository;

import com.klc.daloopintegration.entities.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<SessionInfo, UUID> {
}
