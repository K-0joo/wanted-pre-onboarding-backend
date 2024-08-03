package wanted.recruit.repository;

import org.springframework.data.repository.CrudRepository;
import wanted.recruit.entity.Employment;

public interface EmploymentRepository extends CrudRepository<Employment, Long> {
}
