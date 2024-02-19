package com.klc.daloopintegration.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Hook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;

    private String eventType;

    @CreationTimestamp
    private LocalDateTime createdDate;


    private String stationInfo;

    private String connectivity;







}
