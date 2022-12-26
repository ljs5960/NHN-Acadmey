package com.nhnacademy.resident.service;

import com.nhnacademy.resident.entity.FamilyRelationship;

public interface FamilyRelationshipService {
    FamilyRelationship createFamilyRelationship(FamilyRelationship familyRelationship);

    FamilyRelationship updateFamilyRelationship(FamilyRelationship familyRelationship);

    void deleteFamilyRelationship(Long serialNumber, Long familySerialNumber);
}
