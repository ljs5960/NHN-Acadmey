package com.nhnacademy.resident.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resident")
public class Resident {
    @Id
    @Column(name = "resident_serial_number", length = 11, nullable = false)
    private Long residentSerialNumber;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "resident_registration_number", length = 300 , nullable = false)
    private String residentRegistrationNumber;

    @Column(name = "gender_code", length = 20 , nullable = false)
    private String genderCode;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code", length = 20 , nullable = false)
    private String birthPlaceCode;

    @Column(name = "registration_base_address", length = 500 , nullable = false)
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code", length = 20)
    private String deathPlaceCode;

    @Column(name = "death_place_address", length = 500)
    private String deathPlaceAddress;

    @Column(name = "id", length = 20, nullable = false)
    private String id;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "email", length = 20, nullable = false)
    private String email;

    @Column(name = "authority", nullable = false)
    private String authority;
}
