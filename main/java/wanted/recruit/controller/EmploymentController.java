package wanted.recruit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wanted.recruit.dto.EmployForm;
import wanted.recruit.dto.RecruitDto;
import wanted.recruit.entity.Company;
import wanted.recruit.entity.Employment;
import wanted.recruit.repository.CompanyRepository;
import wanted.recruit.repository.EmploymentRepository;

import java.util.List;
import java.util.Optional;

@Slf4j // 로깅 기능을 위한 어노테이션 추가
@RestController
@RequestMapping("/recruit")
public class EmploymentController {
    @Autowired
    private EmploymentRepository employmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/add")
    public String addEmployment(@RequestBody EmployForm form){
        log.info(form.toString());

        // Company 객체에 아무것도 없다면 예뢰를 던진다.
        if (form.getCompany() == null) {
            throw new RuntimeException("Company information is missing");
        }

        // Company 개체를 데이터 베이스에서 조회한다.
        Company company = companyRepository.findById(form.getCompany().getCompany_id())
                .orElseThrow(() -> new RuntimeException("company not found"));

        // DTO를 엔티티로 변환
        Employment employment = form.toEntity();
        employment.setCompany(company); // 조회된 Company 객체 설정
        log.info(employment.toString());

        Employment saved = employmentRepository.save(employment);
        log.info(saved.toString());

        // 리파지터리로 엔티티를 DB에 저장
        return  "{\"message\": \"Employment added successfully\"}";
    }

//    @PostMapping("/edit/{id}")
//    public String editEmployment(@PathVariable Long id){
//        Employment employmentEntity = employmentRepository.findById(id).orElse(null);
//        return "";
//    }

    @DeleteMapping("/delete")
    public String deleteEmployment(EmployForm form){
        return "";
    }

//    @GetMapping("/list")
//    public List<RecruitDto> list(){
//        // 모든 데이터 가져오기
//        List<Employment> employments = (List<Employment>) employmentRepository.findAll();
//
//        return employments.stream().map(employment -> {
//            var company = employment.getCompany();
//
//            return new RecruitDto(
//                    employment.getEmployment_id(),
//                    company != null ? company.getCompany_name()
//            )
//        });
//    }

//    @GetMapping("/details/{id}")
//    public String detail(@PathVariable Long id){
//        log.info("id = " + id);
//
//        // id를 조회해 데이터 가져오기
//        Employment employmentEntity = employmentRepository.findById(id).orElse(null);
//
//        return "";
//    }
}
