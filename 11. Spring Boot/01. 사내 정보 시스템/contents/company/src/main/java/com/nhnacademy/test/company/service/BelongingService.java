package com.nhnacademy.test.company.service;

import com.nhnacademy.test.company.entity.Belonging;
import java.util.List;

public interface BelongingService {
    List<Belonging> getBelongingByDepartmentId(List<String> departmentIds);
}
