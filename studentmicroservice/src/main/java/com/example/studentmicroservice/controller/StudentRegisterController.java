package com.example.studentmicroservice.controller;

import com.example.commons.beans.Student;
import com.example.commons.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students/register")
public class StudentRegisterController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        if (studentRepository.existsByDocumentNumber(student.getDocumentNumber())) {
            return ResponseEntity.badRequest().body("Student with this document number already exists.");
        }

        student.setRegistrationNumber(UUID.randomUUID().toString());
        studentRepository.save(student);
        return ResponseEntity.ok("Student registered successfully with registration number: " + student.getRegistrationNumber());
    }
}