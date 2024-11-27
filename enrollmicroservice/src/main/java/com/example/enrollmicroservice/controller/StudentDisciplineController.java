package com.example.enrollmicroservice.controller;

import com.example.commons.beans.*;
import com.example.commons.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
public class StudentDisciplineController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @PostMapping
    public ResponseEntity<String> enrollStudent(@RequestParam Long studentId, @RequestParam Long disciplineId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Discipline> disciplineOpt = disciplineRepository.findById(disciplineId);

        if (studentOpt.isEmpty() || disciplineOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Student or Discipline not found.");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(studentOpt.get());
        enrollment.setDiscipline(disciplineOpt.get());
        enrollmentRepository.save(enrollment);

        return ResponseEntity.ok("Student successfully enrolled in the discipline.");
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Enrollment> enrollments = enrollmentRepository.findByStudent(studentOpt.get());
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/discipline/{disciplineId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByDiscipline(@PathVariable Long disciplineId) {
        Optional<Discipline> disciplineOpt = disciplineRepository.findById(disciplineId);
        if (disciplineOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Enrollment> enrollments = enrollmentRepository.findByDiscipline(disciplineOpt.get());
        return ResponseEntity.ok(enrollments);
    }
}