package com.example.commons.repository;

import com.example.commons.beans.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    boolean existsByCodeAndClassGroup(String code, String classGroup);
}
