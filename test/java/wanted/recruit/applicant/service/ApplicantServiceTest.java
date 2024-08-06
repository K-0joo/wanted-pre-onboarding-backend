package wanted.recruit.applicant.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wanted.recruit.applicant.dto.ApplicantDto;
import wanted.recruit.applicant.entity.Applicant;
import wanted.recruit.applicant.repository.ApplicantRepository;
import wanted.recruit.company.entity.Company;
import wanted.recruit.employment.dto.EmploymentDto;
import wanted.recruit.employment.entity.Employment;
import wanted.recruit.employment.repository.EmploymentRepository;

// 앞으로 사용할 클래스 패키지를 예비로 임포트
// File -> Settings -> Editor -> General -> Auto Import 선택하기
// Optimize imports on the fly 체크 해제
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스를 스프링 부트와 연동해 테스트
class ApplicantServiceTest {

    @Autowired
    ApplicantService applicantService; //applicantService 객체 주입

    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

    private Employment employment;


    @Test
    void findAllEmployments() {
        // 1. 예상 데이터
        // 회사 id 1L로 통일해서 테스트해보기
        Company company = new Company();
        company.setCompanyId(1L);
        company.setName("원티드랩");
        company.setCountry("한국");
        company.setRegion("서울");
        
        Employment a = new Employment(1L, company, "백엔드 주니어 개발자", 1500000, "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..", "Python");
        Employment b = new Employment(2L, company, "Django 백엔드 개발자", 1000000, "네이버에서 백엔드 개발자를 채용합니다. 자격요건은..", "Django");
        List<Employment> expected = new ArrayList<>(Arrays.asList(a, b));

        // 2. 실제 데이터sfsdff
        List<Employment> employments = applicantService.findAllEmployments();

        // 3. 비교 및 검증
        assertEquals(expected.toString(), employments.toString());
    }

    @Test
    void detailEmployment_성공() {
        // 1. 예상 데이터
        Long id = 1L;

        Company company = new Company();
        company.setCompanyId(1L);
        company.setName("원티드랩");
        company.setCountry("한국");
        company.setRegion("서울");

        Employment expected = new Employment(id, company, "백엔드 주니어 개발자", 1500000, "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..", "Python");

        // 2. 실제 데이터sfsdff
        Employment employment = applicantService.detailEmployment(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), employment.toString());
    }

    @Test
    void detailEmployment_실패() {
        // 1. 예상 데이터
        Long id = 2L;

        Company company = new Company();
        company.setCompanyId(1L);
        company.setName("원티드랩");
        company.setCountry("한국");
        company.setRegion("서울");

        Employment expected = new Employment(id, company, "백엔드 주니어 개발자", 1500000, "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..", "Python");

        // 2. 실제 데이터sfsdff
        Employment employment = applicantService.detailEmployment(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), employment.toString());

        // 비교하는 데이터들이 틀린 값이라고 뜨면 실패 테스트 성공
    }

    @Test
    void detailEmployment_실패_존재하지_않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Employment expected = null;

        // 2. 실제 데이터
        Employment employment = applicantService.detailEmployment(id);

        // 3. 비교 및 검증
        assertEquals(expected, employment);

        // 만약 log로 "채용 공고를 찾을 수 없습니다!"가 뜬다면 테스트 성공
    }

    @Test
    void findEmployments_성공() {
        List<Employment> list = applicantService.findEmployments("원티드");

        assertEquals(2, list.size());
        assertEquals("백엔드 주니어 개발자", list.get(0).getPosition());
        assertEquals("원티드랩", list.get(0).getCompany().getName());
    }

    @Test
    void findEmployments_실패() {
        List<Employment> list = applicantService.findEmployments("원티드");

        assertEquals(2, list.size());
        assertEquals("백엔드 개발자", list.get(0).getPosition());
        assertEquals("원티드랩", list.get(0).getCompany().getName());
    }

    @Test
    void findEmployments_실패_회사공고_없음() {
        List<Employment> list = applicantService.findEmployments("CJ올리브영");

        assertTrue(list.isEmpty(), "검색 결과가 비어있어야 합니다.");
    }


    @BeforeEach
    public void setup() {
        // Employment 엔티티 초기화 및 저장
        employment = new Employment();
        employment.setId(4L); // ID를 4로 설정 -> 선제 조건 : 이미 있는 공고에서 지원 안한 데이터인지 확인하고 테스트할 것
        employment.setPosition("백엔드 개발자");
        employment.setCarrot(5000000);
        employment.setContent("테스트 공고");
        employment.setUsedTechnique("Spring Boot");

        employmentRepository.save(employment);
    }

    @Test
    void apply_Success() {
        // ApplicantDto 객체 생성
        ApplicantDto dto = new ApplicantDto(null, employment.getId(), 1L);

        // 지원 신청
        ApplicantDto createdDto = applicantService.apply(employment.getId(), 1L, dto);

        // 검증
        assertNotNull(createdDto.getApplicantId()); // ID가 생성되어야 함
        assertEquals(employment.getId(), createdDto.getEmploymentId());
        assertEquals(1L, createdDto.getUserId());

        // Applicant 엔티티가 데이터베이스에 저장되었는지 검증
        Applicant applicant = applicantRepository.findById(createdDto.getApplicantId())
                .orElseThrow(() -> new IllegalArgumentException("지원자를 찾을 수 없습니다."));
        assertEquals(employment.getId(), applicant.getEmployment().getId());
        assertEquals(1L, applicant.getUserId());
    }

    @Test
    void apply_Fail_DuplicateApplication() {
        // 이미 지원된 기록을 생성
        ApplicantDto dto = new ApplicantDto(null, employment.getId(), 1L);
        applicantService.apply(employment.getId(), 1L, dto);

        // 중복 지원 시도
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            applicantService.apply(employment.getId(), 1L, dto);
        });

        assertEquals("이미 지원한 공고입니다.", exception.getMessage());
    }

    @Test
    void apply_Fail_InvalidEmploymentId() {
        // 잘못된 공고 ID로 지원 시도
        ApplicantDto dto = new ApplicantDto(null, 999L, 1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            applicantService.apply(999L, 1L, dto);
        });

        assertEquals("공고 지원 실패! 대상 공고가 없습니다.", exception.getMessage());
    }
}
