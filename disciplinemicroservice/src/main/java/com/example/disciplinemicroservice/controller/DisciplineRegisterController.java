package com.example.disciplinemicroservice.controller;

import com.example.commons.beans.Discipline;
import com.example.commons.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplines/register")
public class DisciplineRegisterController {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @PostMapping
    public ResponseEntity<String> registerDiscipline(@RequestBody Discipline discipline) {
        if (disciplineRepository.existsByCodeAndClassGroup(discipline.getCode(), discipline.getClassGroup())) {
            return ResponseEntity.badRequest().body("Discipline with this code and class group already exists.");
        }
        disciplineRepository.save(discipline);
        return ResponseEntity.ok("Discipline registered successfully.");
    }
}