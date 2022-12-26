package com.nhnacademy.resident.service;

import com.nhnacademy.resident.entity.FamilyRelationship;
import com.nhnacademy.resident.repository.FamilyRelationshipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;

    public FamilyRelationshipServiceImpl(FamilyRelationshipRepository familyRelationshipRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
    }

    @Transactional
    @Override
    public FamilyRelationship createFamilyRelationship(FamilyRelationship familyRelationship) {
        return familyRelationshipRepository.save(familyRelationship);
    }

    @Transactional
    @Override
    public FamilyRelationship updateFamilyRelationship(FamilyRelationship familyRelationship) {
        return familyRelationshipRepository.save(familyRelationship);
    }

    @Override
    public void deleteFamilyRelationship(Long serialNumber, Long familySerialNumber) {
        familyRelationshipRepository.deleteColumnWhereFamilySerialNumber(serialNumber, familySerialNumber);
    }
}
