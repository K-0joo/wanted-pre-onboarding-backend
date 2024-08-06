package wanted.recruit.applicant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import wanted.recruit.applicant.dto.ApplicantDto;
import wanted.recruit.applicant.dto.RecruitDetailsDto;
import wanted.recruit.applicant.dto.RecruitDto;
import wanted.recruit.applicant.service.ApplicantService;
import wanted.recruit.employment.entity.Employment;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/recruit")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    // 모든 공고 데이터 리스트 조회 기능
    @GetMapping("/list")
    public List<RecruitDto> list() {
        // 모든 공고 데이터 가져오기

        List<Employment> employments = applicantService.findAllEmployments();
        return employments.stream()
                .map(RecruitDto::allEmployments)
                .collect(Collectors.toList());
    }

    // 특정 공고에 대한 자세한 내용 조회 기능
    @GetMapping("/details/{id}")
    public RecruitDetailsDto detail(@PathVariable Long id){
        // 공고의 id로 조회
        log.info("id = " + id);

        // id를 조회해 데이터 가져오기
        Employment employment = applicantService.detailEmployment(id);

        return RecruitDetailsDto.detailEmployment(employment);
    }

    @GetMapping("/search")
    public List<RecruitDto> search(@RequestParam(value="search", required = false) String search){

        List<Employment> employments = applicantService.findEmployments(search);
        log.info("Search result: " + employments);

        return employments.stream()
                .map(RecruitDto::allEmployments)
                .collect(Collectors.toList());
    }

    @PostMapping("/apply/{employmentId}/{userId}")
    public ResponseEntity<ApplicantDto> apply(@PathVariable Long employmentId, @PathVariable Long userId, @RequestBody ApplicantDto dto){
        ApplicantDto createDto = applicantService.apply(employmentId, userId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }
}
