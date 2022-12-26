package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, Long> {
    @Modifying
    @Transactional
    @Query(
        value =
            "DELETE FROM family_relationship f " +
            "WHERE f.base_resident_serial_number = :serialNumber AND f.family_resident_serial_number = :familySerialNumber",
        nativeQuery = true)
    void deleteColumnWhereFamilySerialNumber(@Param("serialNumber") Long serialNumber,
                                             @Param("familySerialNumber") Long familySerialNumber);
}
