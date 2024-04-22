package com.klc.daloopintegration.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class AttributesDTO {

    private String activityId;
    private String evseName;
    private String userContractCode;
    private String tokenOperatorCode;
    private String cemeContractCode;
    private String tokenCode;
    private String transactionBusinessUnitCode;
    private String cpeCode;
    private String cemeCode;
    private String opcCode;
    private String cseCode;
    private String chargeTypeCode;
    private String municipalityCode;
    private String assetOperatorGroupCode;
    private int idDayUtc;
    private int idDayLocal;
    private String idTimeUtc;
    private String year;
    private String yearMonth;
    private String weekDay;
    private String hour;
    private String meterErrorStatus;
    private int numMeterValues;
    private double totalDuration;
    private double sumRawEnergy;
    private double sum15mEnergy;
    private double totalEnergy;
    private double maxRawPower;
    private double max15mPower;
    private double averagePower;
    private int activityCount;
    private double estimatedCo2Avoided;
}
