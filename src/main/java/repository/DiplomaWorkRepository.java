package repository;

import entity.DiplomaWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaWorkRepository extends JpaRepository<DiplomaWork, Long> {
}
