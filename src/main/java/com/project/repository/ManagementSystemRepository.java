package com.project.repository;

import com.project.entity.ManagementSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementSystemRepository extends JpaRepository<ManagementSystem, Long> {
}
