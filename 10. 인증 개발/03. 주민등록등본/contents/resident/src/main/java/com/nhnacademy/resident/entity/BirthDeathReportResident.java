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
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {
    @EmbeddedId
    private BirthDeathReportResidentId birthDeathReportResidentId;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident residentSerialNumber;

    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResidentSerialNumber;

    @Column(name = "birth_death_report_date")
    private LocalDate birthDeathReportDate;

    @Column(name = "birth_report_qualifications_code")
    private String birthReportQualificationCode;

    @Column(name = "death_report_qualifications_code")
    private String deathReportQualificationCode;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class BirthDeathReportResidentId implements Serializable {
        @Column(name = "birth_death_type_code")
        private Long birthDeathTypeCode;

        private Long residentSerialNumber;
    }
}

