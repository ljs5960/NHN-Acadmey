package com.nhnacademy.resident.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "family_relationship")
public class FamilyRelationship {
    @EmbeddedId
    private FamilyRelationshipId familyRelationshipId;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number")
    private Resident baseResidentSerialNumber;

    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class FamilyRelationshipId implements Serializable {
        @Column(name = "family_resident_serial_number")
        private Long familyResidentSerialNumber;

        private Long baseResidentSerialNumber;
    }

    @Builder
    public FamilyRelationship(FamilyRelationshipId familyRelationshipId,
                              Resident baseResidentSerialNumber,
                              String familyRelationshipCode) {
        this.familyRelationshipId = familyRelationshipId;
        this.baseResidentSerialNumber = baseResidentSerialNumber;
        this.familyRelationshipCode = familyRelationshipCode;
    }
}
