package com.nhnacademy.score.controller;

import com.nhnacademy.score.domain.Student;
import com.nhnacademy.score.domain.StudentModifyRequest;
import com.nhnacademy.score.exception.StudentNotFoundException;
import com.nhnacademy.score.exception.ValidationFailedException;
import com.nhnacademy.score.repository.StudentRepository;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @ModelAttribute("student")
    public Student getStudent(@PathVariable("studentName") String studentName) {
        Student student = studentRepository.getStudent(studentName);

        if (Objects.isNull(student)) {
            throw new StudentNotFoundException();
        }

        return student;
    }

    @GetMapping("/{studentName}")
    public String viewStudent(@ModelAttribute("student") Student student,
                              @PathVariable("studentName") String studentName,
                              Model model) {
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException();
        }

        student = studentRepository.getStudent(studentName);
        model.addAttribute("student", student);

        return "studentView";
    }

    @GetMapping(value = "/{studentName}", params = "hideScore")
    public String viewHiddenScoreStudent(@ModelAttribute("student") Student student,
                                         @PathVariable("studentName") String studentName,
                                         @RequestParam String hideScore,
                                         Model model) {
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException();
        }

        student = studentRepository.getStudent(studentName);

        if (Objects.equals(hideScore, "yes")) {
            return "hiddenScoreStudentView";
        }

        model.addAttribute("student", student);
        return "studentView";
    }

    @GetMapping("/{studentName}/modify")
    public String studentModifyForm(@ModelAttribute Student student, ModelMap modelMap) {
        modelMap.put("student", student);
        return "studentModify";
    }

    @PostMapping("/{studentName}/modify")
    public String modifyUser(@ModelAttribute("student") Student student,
                             @Valid @ModelAttribute StudentModifyRequest studentModifyRequest,
                             BindingResult bindingResult,
                             ModelAndView modelAndView) {
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException();
        }

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("exception", new ValidationFailedException(bindingResult));
            return "error";
        }

        student.setName(student.getName());
        student.setEmail(student.getEmail());
        student.setScore(student.getScore());
        student.setComment(student.getComment());

        studentRepository.modifyStudent(student);

        modelAndView.addObject("student", student);
        return "studentView";
    }


    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "not found")
    public String StudentNotFoundExceptionHandler(StudentNotFoundException ex, Model model) {
        model.addAttribute("exception", ex);
        return "error";
    }
}
