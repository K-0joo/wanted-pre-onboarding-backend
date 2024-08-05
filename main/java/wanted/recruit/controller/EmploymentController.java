package wanted.recruit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.recruit.dto.EmploymentDto;
import wanted.recruit.entity.Company;
import wanted.recruit.entity.Employment;
import wanted.recruit.service.EmploymentService;

@Slf4j // 로깅 기능을 위한 어노테이션 추가
@RestController
@RequestMapping("/recruit")
public class EmploymentController {
    @Autowired
    private EmploymentService employmentService;

    // 1. 공고 생성
    @PostMapping("/add/{company_id}")
    public ResponseEntity<EmploymentDto> add(@PathVariable Long company_id, @RequestBody EmploymentDto dto){
        // 서비스에 위임
        EmploymentDto createdDto = employmentService.create(company_id, dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 2. 공고 수정
    @PatchMapping("/edit/{id}")
    public ResponseEntity<EmploymentDto> edit(@PathVariable Long id, @RequestBody EmploymentDto dto){
        // 서비스에 위임
        EmploymentDto updatedDto = employmentService.updated(id, dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
   }

   // 3. 댓글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmploymentDto> delete(@PathVariable Long id){
        // 서비스에 위임
        EmploymentDto deletdDto = employmentService.delete(id);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletdDto);
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
