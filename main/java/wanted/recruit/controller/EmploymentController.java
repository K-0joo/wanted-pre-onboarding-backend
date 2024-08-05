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

//    @PatchMapping("/edit/{id}")
//    public ResponseEntity<Employment> editEmployment(@PathVariable Long id, @RequestBody EmploymentDto dto){
//        // DTO 엔티티 변환하기
//        Employment employment = dto.toEntity();
//        log.info("id : {}, employment : {}", id, employment.toString());
//
//        // 타깃 조회하기
//        Employment target = employmentRepository.findById(id).orElse(null);
//
//        // 잘못된 요청 처리하기
//        if(target == null || id != employment.getEmployment_id()){
//            // 400 잘못된 요청 응답!
//            log.info("잘못된 요청! id : {}, employment : {}", id, employment.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//        }
//
//        // 업데이트 및 정상 응답(200)하기
//        target.patch(employment);
//        Employment updated = employmentRepository.save(employment);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//
//        //Employment employmentEntity = employmentRepository.findById(id).orElse(null);
//        //return "{\"message\": \"Employment edited successfully\"}";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Employment> deleteEmployment(@PathVariable Long id){
//        // 삭제할 대상 찾기
//        Employment target = employmentRepository.findById(id).orElse(null);
//
//        // 잘못된 요청 처리하기
//        if(target == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 대상 삭제하기
//        employmentRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//
//
//        //return "{\"message\": \"Employment deleted successfully\"}";
//    }


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
