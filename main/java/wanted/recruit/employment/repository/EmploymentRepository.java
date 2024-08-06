package wanted.recruit.employment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wanted.recruit.employment.entity.Employment;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    @Query("SELECT e FROM Employment e WHERE e.company.name LIKE %:name%")
    List<Employment> findByCompanyNameContaining(@Param("name") String name);
}
