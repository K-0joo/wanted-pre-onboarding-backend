package wanted.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import wanted.recruit.entity.Employment;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    // 회사의 모든 공고 조회
    @Query(value = "SELECT * FROM employment WHERE company_id = :company_id", nativeQuery = true)
    List<Employment> findEmploymentBy(Long company_id);

}
