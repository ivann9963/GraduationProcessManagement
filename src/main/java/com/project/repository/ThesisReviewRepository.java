package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisReviewRepository extends JpaRepository<com.project.entity.ThesisReview, Long> {
}
