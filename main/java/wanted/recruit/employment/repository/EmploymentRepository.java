package wanted.recruit.employment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wanted.recruit.employment.entity.Employment;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {
}
