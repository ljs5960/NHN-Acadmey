package com.nhnacademy.test.company.service;

import com.nhnacademy.test.company.entity.Belonging;
import com.nhnacademy.test.company.repository.BelongingRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BelongingServiceImpl implements BelongingService {
    private final BelongingRepository belongingRepository;

    public BelongingServiceImpl(BelongingRepository belongingRepository) {
        this.belongingRepository = belongingRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Belonging> getBelongingByDepartmentId(List<String> departmentIds) {
        List<Belonging> belongings = new ArrayList<>();

        for(String departmentId : departmentIds) {
            belongings.addAll(belongingRepository.findByDepartmentId(departmentId));
//            belongings.add(belongingRepository.findByDepartmentId(departmentId).);
        }

        return belongings;
    }
}

