package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.dto.FamilyRelationshipRequest;
import com.nhnacademy.resident.entity.FamilyRelationship;
import com.nhnacademy.resident.exception.ValidationFailedException;
import com.nhnacademy.resident.service.FamilyRelationshipService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyRelationshipRestController {
    private final FamilyRelationshipService familyRelationshipService;

    public FamilyRelationshipRestController(FamilyRelationshipService familyRelationshipService) {
        this.familyRelationshipService = familyRelationshipService;
    }

    /*
    가족관계 등록
     */
    @PostMapping("/residents/{serialNumber}/relationship")
    public FamilyRelationship createFamilyRelationship(@PathVariable("serialNumber") Long serialNumber,
                                                       @Valid @RequestBody FamilyRelationshipRequest request,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return familyRelationshipService.createFamilyRelationship(
            FamilyRelationship.builder().build()
        );
    }

    /*
    가족관계 수정
     */
    @PutMapping("/residents/{serialNumber}/relationship/{familySerialNumber}")
    public FamilyRelationship updateFamilyRelationship(@PathVariable("serialNumber") Long serialNumber,
                                                       @PathVariable("familySerialNumber") Long familySerialNumber,
                                                       @Valid @RequestBody FamilyRelationshipRequest request,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return familyRelationshipService.updateFamilyRelationship(
            FamilyRelationship.builder().build()
        );
    }

    /*
    가족관계 삭제
     */
    @DeleteMapping("/residents/{serialNumber}/relationship/{familySerialNumber}")
    public void deleteFamilyRelationship(@PathVariable("serialNumber") Long serialNumber,
                                         @PathVariable("familySerialNumber") Long familySerialNumber) {
        familyRelationshipService.deleteFamilyRelationship(serialNumber, familySerialNumber);
    }
}
