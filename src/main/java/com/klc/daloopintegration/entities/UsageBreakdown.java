package com.klc.daloopintegration.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsageBreakdown {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "business_unit")
    private String businessUnit;

    @Column(name = "usage")
    private String usage;

    @Column(name = "id_fare")
    private String idFare;

    @Column(name = "init_date")
    private OffsetDateTime initDate;

    @Column(name = "end_date")
    private OffsetDateTime endDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "total_duration")
    private Integer totalDuration;

    @Column(name = "voltage_level")
    private String voltageLevel;

    @Column(name = "region")
    private String region;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "usage_breakdown_id")
    private Set<Vat> vats;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "usage_breakdown_id")
    private Set<Detail> details;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_last_update")
    private LocalDateTime dateLastUpdate;
}
