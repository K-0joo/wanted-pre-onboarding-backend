package wanted.recruit.repository;

import org.springframework.data.repository.CrudRepository;
import wanted.recruit.entity.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
