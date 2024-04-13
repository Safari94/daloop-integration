package com.klc.daloopintegration.entities;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usage_breakdown")
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
    private String initDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "total_price")
    private String totalPrice;

    @Column(name = "total_duration")
    private String totalDuration;

    @Column(name = "voltage_level")
    private String voltageLevel;

    @Column(name = "region")
    private String region;

    @OneToMany(mappedBy = "usageBreakdown", cascade = CascadeType.ALL)
    private List<Vat> vats;

    @OneToMany(mappedBy = "usageBreakdown", cascade = CascadeType.ALL)
    private List<Detail> details;

    @Column(name = "date_created")
    private String dateCreated;

    @Column(name = "date_last_update")
    private String dateLastUpdate;
}
