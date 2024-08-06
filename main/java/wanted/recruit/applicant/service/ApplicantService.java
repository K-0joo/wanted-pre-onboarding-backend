package wanted.recruit.applicant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.recruit.employment.entity.Employment;
import wanted.recruit.employment.repository.EmploymentRepository;

import java.util.List;

@Service
public class ApplicantService {
    @Autowired
    private EmploymentRepository employmentRepository;

    @Transactional(readOnly = true)
    public List<Employment> findAllEmployments() {
        return employmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employment detailEmployment(Long id){
        return employmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("채용 공고를 찾을 수 없습니다!"));
    }

    @Transactional(readOnly = true)
    public List<Employment> findEmployments(String search) {
        if (search == null || search.isEmpty()) {
            throw new IllegalArgumentException("해당하는 회사 또는 공고를 찾을 수 없습니다.");
        }


        return employmentRepository.findBySearchContaining(search);
    }
}
