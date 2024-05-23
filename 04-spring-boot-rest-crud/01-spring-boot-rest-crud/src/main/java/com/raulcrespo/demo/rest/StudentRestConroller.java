package com.raulcrespo.demo.rest;

import com.raulcrespo.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestConroller {

    List<Student> students = new ArrayList<>();

    @PostConstruct
    public void loadData() {
        students.add(new Student(1, "raul", "crespo"));
        students.add(new Student(2, "luar", "opserc"));
        students.add(new Student(3, "ralph", "lauren"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if(studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return students.get(studentId);
    }


}
