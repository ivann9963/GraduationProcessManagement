package com.project.repository;

import com.project.entity.DiplomaAcceptance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaAcceptanceRepository extends JpaRepository<DiplomaAcceptance, Long> {
}
