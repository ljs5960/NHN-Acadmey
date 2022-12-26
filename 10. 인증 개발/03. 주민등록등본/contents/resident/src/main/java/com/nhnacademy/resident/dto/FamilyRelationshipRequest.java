package com.nhnacademy.resident.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FamilyRelationshipRequest {
    @NotNull
    Long familyResidentSerialNumber;

    @NotNull
    String familyrelationshipCode;
}
