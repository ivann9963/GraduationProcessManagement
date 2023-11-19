package com.project.repository;

import com.project.entity.DiplomaDefense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaDefenseRepository extends JpaRepository<DiplomaDefense, Long> {
}
