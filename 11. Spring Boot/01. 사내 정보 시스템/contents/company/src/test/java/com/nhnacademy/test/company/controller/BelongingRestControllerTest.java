package com.nhnacademy.test.company.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.test.company.entity.Belonging;
import com.nhnacademy.test.company.entity.Department;
import com.nhnacademy.test.company.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class BelongingRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("200_STATUS")
    void testGetBelonging_if_200Status() throws Exception {
        // given
        Belonging belonging = Belonging.builder()
            .departmentId(new Department("L1001", "백엔드1팀"))
            .employeeId(new Employee(20202201L, "김이름"))
            .build();

        // when then
        mockMvc.perform(get("/department-members?departmentIds=L1001"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andDo(print());
    }

    @Test
    @Order(2)
    @DisplayName("400_STATUS")
    void testGetBelonging_if_400Status() throws Exception {
        // given when then
        mockMvc.perform(get("/department-members?departmentIds="))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}