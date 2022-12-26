package com.nhnacademy.score.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.score.domain.Student;
import com.nhnacademy.score.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class StudentRestControllerTest {
    private MockMvc mockMvc;

    private StudentRepository studentRepository;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentRestController(studentRepository)).build();
    }

    @Test
    void testStudentExists_ifGetMethod_success() throws Exception {
        Student student = new Student("test", "test@test.com", 50, "testComment");

        when(studentRepository.getStudent("test")).thenReturn(student);

        mockMvc.perform(get("/students/{studentName}", "test")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("test"))
            .andExpect(jsonPath("$.email").value("test@test.com"))
            .andExpect(jsonPath("$.score").value(50))
            .andExpect(jsonPath("$.comment").value("testComment"));
    }

    @Test
    void testStudentExists_ifGetMethod_fail() throws Exception {
        Student student = new Student("test", "test@test.com", 50, "testComment");

        when(studentRepository.getStudent("test")).thenReturn(student);

        mockMvc.perform(get("/students/{studentName}", "invalidTest")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void testStudentExists_ifPostMethod() throws Exception {
        Student student = new Student("test", "test@test.com", 50, "testComment");

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(status().isCreated());
    }

    @Test
    void testStudentExists_ifPutMethod() throws Exception {
        Student student = new Student("test", "test@test.com", 50, "testComment");

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(student);

        mockMvc.perform(put("/students/{students}", "test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(status().isCreated());
    }
}