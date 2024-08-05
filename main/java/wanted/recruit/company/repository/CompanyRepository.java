package wanted.recruit.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.recruit.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
