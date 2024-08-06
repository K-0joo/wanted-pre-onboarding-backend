package wanted.recruit.applicant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.recruit.applicant.dto.ApplicantDto;
import wanted.recruit.applicant.entity.Applicant;
import wanted.recruit.applicant.repository.ApplicantRepository;
import wanted.recruit.employment.entity.Employment;
import wanted.recruit.employment.repository.EmploymentRepository;

import java.util.List;

@Service
public class ApplicantService {
    @Autowired
    private EmploymentRepository employmentRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

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

    @Transactional
    public ApplicantDto apply(Long employmentId, Long userId, ApplicantDto dto){
        // 1. 공고 조회 및 예외 발생
        Employment employment = employmentRepository.findById(employmentId)
                .orElseThrow(() -> new IllegalArgumentException("공고 지원 실패! 대상 공고가 없습니다."));

        // 지원한 기록이 이미 있는지 확인
        if(applicantRepository.findByEmploymentIdAndUserId(employmentId, userId).isPresent()){
            throw new IllegalArgumentException("이미 지원한 공고입니다.");
        }

        // 2. 사용자 엔티티 생성
        //Applicant applicant = Applicant.createApply(dto, employment);
        Applicant applicant = new Applicant();  // 생성자를 직접 호출
        applicant.setEmployment(employment);
        applicant.setUserId(userId);

        // 3. 사용자 엔티티를 DB에 저장
        Applicant created = applicantRepository.save(applicant);

        // 4. DTO로 변환해 반환
        return ApplicantDto.createApplyDto(created);

    }
}
