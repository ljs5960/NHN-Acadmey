package com.nhnacademy.resident.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ResidentRequestDto {
    @NotNull
    Long residentSerialNumber;

    @NotBlank
    String name;

    @NotBlank
    String residentRegistrationNumber;

    @NotBlank
    String genderCode;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:SS")
    LocalDateTime birthDate;

    @NotBlank
    String birthPlaceCode;

    @NotBlank
    String registrationBaseAddress;

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:SS")
    LocalDateTime deathDate;

    String deathPlaceCode;
    String deathPlaceAddress;

    @NotBlank
    String id;

    @NotBlank
    String password;

    @NotBlank
    @Email
    String email;

    @NotBlank
    String authority;
}
