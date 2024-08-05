package wanted.recruit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.recruit.dto.EmploymentDto;
import wanted.recruit.dto.RecruitDto;
import wanted.recruit.entity.Company;
import wanted.recruit.entity.Employment;
import wanted.recruit.repository.CompanyRepository;
import wanted.recruit.repository.EmploymentRepository;
import wanted.recruit.service.EmploymentService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/recruit")
public class ApplicantController {

    @Autowired
    private EmploymentService employmentService;

    @GetMapping("/list")
    public List<RecruitDto> list() {
        // 모든 공고 데이터 가져오기

        List<Employment> employments = employmentService.findAllEmployments();
        return employments.stream()
                .map(RecruitDto::AllEmploymentList)
                .collect(Collectors.toList());
    }

    @GetMapping("/details/{id}")
    public RecruitDto detail(@PathVariable Long id){
//        log.info("id = " + id);
//
//        // id를 조회해 데이터 가져오기
//        Employment employmentEntity = employmentRepository.findById(id).orElse(null);
//
        return "";
    }
}
