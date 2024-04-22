package com.klc.daloopintegration.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usage_breakdown_id")
    private UsageBreakdown usageBreakdown;

    @Column(name = "component")
    private String component;

    @Column(name = "amount_value")
    private Double amountValue;

    @Column(name = "unit")
    private String unit;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "included")
    private boolean included;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_last_update")
    private LocalDateTime dateLastUpdate;

}
