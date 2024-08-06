package wanted.recruit.applicant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.recruit.applicant.entity.Applicant;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Optional<Applicant> findByEmploymentIdAndUserId(Long employmentId, Long userId);
}
