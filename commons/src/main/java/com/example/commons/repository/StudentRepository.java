package com.example.commons.repository;

import com.example.commons.beans.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByDocumentNumber(String documentNumber);
    boolean existsByRegistrationNumber(String registrationNumber);
    List<Student> findByNameContainingIgnoreCase(String name);

}