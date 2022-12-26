package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.entity.Resident;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
    @Query("SELECT r FROM Resident r WHERE r.id=:id")
    Optional<Resident> findById(@Param(value = "id")String id);
}
