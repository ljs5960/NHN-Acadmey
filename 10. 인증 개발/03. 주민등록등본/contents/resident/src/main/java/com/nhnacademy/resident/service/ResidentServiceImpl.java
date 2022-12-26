package com.nhnacademy.resident.service;

import com.nhnacademy.resident.dto.ResidentRequestDto;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.entity.Resident;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Transactional
    @Override
    public Resident createResident(ResidentRequestDto residentRequestDto) {
        Resident resident = new Resident(
            residentRequestDto.getResidentSerialNumber(),
            residentRequestDto.getName(),
            residentRequestDto.getResidentRegistrationNumber(),
            residentRequestDto.getGenderCode(),
            residentRequestDto.getBirthDate(),
            residentRequestDto.getBirthPlaceCode(),
            residentRequestDto.getRegistrationBaseAddress(),
            residentRequestDto.getDeathDate(),
            residentRequestDto.getDeathPlaceCode(),
            residentRequestDto.getDeathPlaceAddress(),
            residentRequestDto.getId(),
            bCryptPasswordEncoder.encode(residentRequestDto.getPassword()),
            residentRequestDto.getEmail(),
            residentRequestDto.getAuthority()
        );

        return residentRepository.save(resident);
    }

    @Transactional
    @Override
    public Resident updateResident(ResidentRequestDto residentRequestDto) {
        Resident resident = new Resident(
            residentRequestDto.getResidentSerialNumber(),
            residentRequestDto.getName(),
            residentRequestDto.getResidentRegistrationNumber(),
            residentRequestDto.getGenderCode(),
            residentRequestDto.getBirthDate(),
            residentRequestDto.getBirthPlaceCode(),
            residentRequestDto.getRegistrationBaseAddress(),
            residentRequestDto.getDeathDate(),
            residentRequestDto.getDeathPlaceCode(),
            residentRequestDto.getDeathPlaceAddress(),
            residentRequestDto.getId(),
            bCryptPasswordEncoder.encode(residentRequestDto.getPassword()),
            residentRequestDto.getEmail(),
            residentRequestDto.getAuthority()
        );

        return residentRepository.save(resident);
    }

    @Override
    public Optional<Resident> getResidentById(Long id) {
        return residentRepository.findById(id);
    }
}
