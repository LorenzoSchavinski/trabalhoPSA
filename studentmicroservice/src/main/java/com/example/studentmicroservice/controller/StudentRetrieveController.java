package com.example.studentmicroservice.controller;

import com.example.commons.beans.Student;
import com.example.commons.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students/retrieve")
public class StudentRetrieveController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/byRegistrationNumber/{registrationNumber}")
    public ResponseEntity<Student> getStudentByRegistrationNumber(@PathVariable String registrationNumber) {
        Optional<Student> student = studentRepository.findById(Long.valueOf(registrationNumber));
        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable String name) {
    List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
    if (students.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(students);
}


    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }
}