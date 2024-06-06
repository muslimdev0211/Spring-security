package com.muslimdev.dars.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "James Maddissom"),
            new Student(3, "Jayme Vardy")
    );

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(path = "/{studentId}")
    public Student getStudent(@PathVariable Integer studentId){
        return STUDENT_LIST.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student" + studentId + "does not exists"));

    }
}
