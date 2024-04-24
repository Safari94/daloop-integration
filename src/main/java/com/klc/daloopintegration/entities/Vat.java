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
public class Vat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "percentage_value")
    private Double percentageValue;

    @Column(name = "amount_value")
    private Double amountValue;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_last_update")
    private LocalDateTime dateLastUpdate;

    @ManyToOne
    @JoinColumn(name = "usage_breakdown_id")
    private UsageBreakdown usageBreakdown;
}
