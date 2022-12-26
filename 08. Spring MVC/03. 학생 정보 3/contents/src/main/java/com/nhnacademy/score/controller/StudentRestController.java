package com.nhnacademy.score.controller;

import com.nhnacademy.score.domain.Student;
import com.nhnacademy.score.domain.StudentModifyRequest;
import com.nhnacademy.score.domain.StudentRegisterRequest;
import com.nhnacademy.score.repository.StudentRepository;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentRestController {
    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus postStudent(@Valid @RequestBody StudentRegisterRequest studentRegisterRequest) {
        studentRepository.addStudent(
            studentRegisterRequest.getName(),
            studentRegisterRequest.getEmail(),
            studentRegisterRequest.getScore(),
            studentRegisterRequest.getComment());

        return HttpStatus.CREATED;
    }

    @GetMapping("/{studentName}")
    public Student getStudent(@PathVariable("studentName") String studentName) {
        return studentRepository.getStudent(studentName);
    }

    @PutMapping("/{studentName}")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus putStudent(@Valid @PathVariable("studentName") String studentName,
                              @RequestBody StudentModifyRequest studentModifyRequest) {
        studentRepository.modifyStudent(
            studentModifyRequest.getName(),
            studentModifyRequest.getEmail(),
            studentModifyRequest.getScore(),
            studentModifyRequest.getComment(),
            getStudent(studentName));

        return HttpStatus.CREATED;
    }
}
