package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.dto.ResidentRequestDto;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.service.ResidentService;
import com.nhnacademy.resident.exception.ValidationFailedException;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResidentRestController {
    private final ResidentService residentService;

    public ResidentRestController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/residents")
    public Resident createResident(@Valid @RequestBody ResidentRequestDto residentRequestDto,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return residentService.createResident(residentRequestDto);
    }

    /*
    주민 수정
     */
    @PutMapping("/residents/{serialNumber}")
    public Resident updateResident(@Valid @RequestBody ResidentRequestDto residentRequestDto,
                                   @PathVariable("serialNumber") Long serialNumber,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return residentService.updateResident(residentRequestDto);
    }

    public Optional<Resident> getResidentById(Long id) {
        return residentService.getResidentById(id);
    }
}