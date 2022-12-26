package com.nhnacademy.resident.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {
    @EmbeddedId
    private HouseholdMovementAddressId householdMovementAddressId;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "house_movement_report_date")
    private Household householdSerialNumber;

    @Column(name = "house_movement_address")
    private String houseMovementAddress;

    @Column(name = "last_address_yn")
    private Boolean lastAddressYN;

    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class HouseholdMovementAddressId implements Serializable {
        @Column(name = "household_serial_number")
        private LocalDate houseMovementReportDate;

        private Long householdSerialNumber;
    }
}
