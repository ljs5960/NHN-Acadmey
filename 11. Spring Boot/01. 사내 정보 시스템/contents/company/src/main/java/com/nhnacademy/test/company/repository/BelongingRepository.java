package com.nhnacademy.test.company.repository;

import com.nhnacademy.test.company.entity.Belonging;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BelongingRepository extends JpaRepository<Belonging, Long> {
    @Query(
        "select b " +
        "from Belonging b " +
            "left outer join Employee e on e.id = b.employeeId.id " +
            "left outer join Department d on d.id = b.departmentId " +
        "where b.departmentId.id = :departmentId"
    )
    List<Belonging> findByDepartmentId(@Param("departmentId") String departmentId);
}
