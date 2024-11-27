package com.example.commons.repository;

import com.example.commons.beans.Enrollment;
import com.example.commons.beans.Student;
import com.example.commons.beans.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByDiscipline(Discipline discipline);
}