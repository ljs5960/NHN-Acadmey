package com.nhnacademy.test.company.controller;

import com.nhnacademy.test.company.entity.Belonging;
import com.nhnacademy.test.company.service.BelongingService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BelongingRestController {
    private final BelongingService belongingService;

    public BelongingRestController(BelongingService belongingService) {
        this.belongingService = belongingService;
    }

    @GetMapping("/department-members")
    public List<Belonging> getBelonging(@RequestParam("departmentIds") List<String> departmentIds,
                                        HttpServletResponse response) {
        if (departmentIds.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return belongingService.getBelongingByDepartmentId(departmentIds);
    }
}
