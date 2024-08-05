package wanted.recruit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.recruit.dto.EmploymentDto;
import wanted.recruit.entity.Company;
import wanted.recruit.entity.Employment;
import wanted.recruit.repository.CompanyRepository;
import wanted.recruit.repository.EmploymentRepository;

@Service
public class EmploymentService {
    @Autowired
    private EmploymentRepository employmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public EmploymentDto create(Long companyId, EmploymentDto dto) {
        // 1. 회사 조회 및 예외 발생
        Company company = companyRepository.findById(companyId)
                .orElseThrow(()-> new IllegalArgumentException("공고 생성 실패! 대상 회사가 없습니다."));

        // 공고 엔티티 생성
        Employment employment = Employment.createEmployment(dto, company);

        // 공고 엔티티를 DB에 저장
        Employment created = employmentRepository.save(employment);

        // DTO로 변환해 반환
        return EmploymentDto.createEmploymentDto(created);
    }

    @Transactional
    public EmploymentDto updated(Long id, EmploymentDto dto) {
        // 1. 공고 조회 및 예외 발생
        Employment target = employmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("공고 수정 실패! 대상 공고가 없습니다."));

        // 2. 공고 수정
        target.patch(dto);

        // 3. DB로 갱신
        Employment updated = employmentRepository.save(target);

        // 4. 공고 엔티티를 DTO로 변환 및 반환
        return EmploymentDto.createEmploymentDto(updated);
    }

    @Transactional
    public EmploymentDto delete(Long id) {
        // 1. 공고 조회 및 예외 발생
        Employment target = employmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("공고 삭제 실패! 대상이 없습니다!"));

        // 2. 공고 삭제
        employmentRepository.delete(target);

        // 3. 삭제 공고를 DTO로 변환 및 반환
        return EmploymentDto.createEmploymentDto(target);
    }
}
