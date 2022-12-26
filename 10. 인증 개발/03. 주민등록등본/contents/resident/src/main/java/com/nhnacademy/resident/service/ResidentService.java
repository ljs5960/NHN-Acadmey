package com.nhnacademy.resident.service;

import com.nhnacademy.resident.dto.ResidentRequestDto;
import com.nhnacademy.resident.entity.Resident;
import java.util.Optional;

public interface ResidentService {
    Resident createResident(ResidentRequestDto residentRequestDto);

    Resident updateResident(ResidentRequestDto residentRequestDto);

    Optional<Resident> getResidentById(Long id);
}
