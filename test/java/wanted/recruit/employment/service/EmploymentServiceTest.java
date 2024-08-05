package wanted.recruit.employment.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wanted.recruit.company.entity.Company;
import wanted.recruit.company.repository.CompanyRepository;
import wanted.recruit.employment.dto.EmploymentDto;
import wanted.recruit.employment.entity.Employment;
import wanted.recruit.employment.repository.EmploymentRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스를 스프링 부트와 연동해 테스트
class EmploymentServiceTest {
    @Autowired
    EmploymentService employmentService;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private EmploymentRepository employmentRepository;

    private Company company;

    @BeforeEach
    void setUp() {
        // 테스트 전에 Company 객체를 준비합니다.
        company = new Company();
        company.setCompanyId(1L);
        company.setName("원티드코리아");
        company.setCountry("한국");
        company.setRegion("부산");
        companyRepository.save(company);
    }


    @Test
    void add_성공() {

        //1. Company 객체를 데이터베이스에 저장
        companyRepository.save(company);

        // 2. EmploymentDto 객체 생성
        Long companyId = company.getCompanyId();
        String position = "백엔드 시니어 개발자";
        int carrot = 5000000;
        String content = "테스트 회사에서 백엔드 시니어 개발자를 채용합니다. 자격요건은...";
        String usedTechnique = "Node.js";

        EmploymentDto dto = new EmploymentDto(null, companyId, position, carrot, content, usedTechnique);

        // 2. 실제 데이터
        EmploymentDto createdDto = employmentService.create(companyId, dto);

        // 3. 검증
        assertNotNull(createdDto.getId()); // ID가 생성되어야 함
        assertEquals(position, createdDto.getPosition());
        assertEquals(carrot, createdDto.getCarrot());
        assertEquals(content, createdDto.getContent());
        assertEquals(usedTechnique, createdDto.getUsedTechnique());

        Employment createdEmployment = employmentRepository.findById(createdDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("공고를 찾을 수 없습니다."));
        assertEquals(companyId, createdEmployment.getCompany().getCompanyId());
    }

    @Test
    void add_실패() {
        // 1. 회사가 없는 경우
        Long companyId = 999L;
        EmploymentDto dto = new EmploymentDto(null, companyId, "포지션", 1000000, "내용", "기술");

        // 2. 실패 예외 발생
        assertThrows(IllegalArgumentException.class, () -> {
            employmentService.create(companyId, dto);
        });
    }

    @Test
    void updated() {
        // 1. 초기 데이터 생성
        Long companyId = 1L;
        Long employmentId = 1L;

        // 초기 공고 DTO 설정
        EmploymentDto initialDto = new EmploymentDto();
        initialDto.setId(employmentId);
        initialDto.setCompanyId(companyId);
        initialDto.setPosition("백엔드 주니어 개발자");
        initialDto.setCarrot(1500000);
        initialDto.setContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");
        initialDto.setUsedTechnique("Python");

        // 2. 수정할 DTO 데이터
        EmploymentDto updateDto = new EmploymentDto();
        updateDto.setId(employmentId);
        updateDto.setCompanyId(companyId);
        updateDto.setPosition("업데이트된 포지션");
        updateDto.setCarrot(2000000);
        updateDto.setContent("업데이트된 내용");
        updateDto.setUsedTechnique("업데이트된 기술");

        // 3. 실제 데이터 생성
        EmploymentDto updatedDto = employmentService.updated(employmentId, updateDto);

        // 4. 검증
        Employment updatedEmployment = employmentRepository.findById(employmentId)
                .orElseThrow(() -> new IllegalArgumentException("공고를 찾을 수 없습니다."));

        assertEquals("업데이트된 포지션", updatedEmployment.getPosition());
        assertEquals(2000000, updatedEmployment.getCarrot());
        assertEquals("업데이트된 내용", updatedEmployment.getContent());
        assertEquals("업데이트된 기술", updatedEmployment.getUsedTechnique());
    }

    @Test
    void delete() {
        // 1. 삭제할 공고의 ID 설정
        Long id = 1L;

        // 2. 삭제할 공고 조회 및 삭제
        EmploymentDto deletedDto = employmentService.delete(id);

        // 3. 삭제된 공고의 DTO가 맞는지 검증
        assertEquals(id, deletedDto.getId());
        assertEquals("백엔드 주니어 개발자", deletedDto.getPosition()); // 원래 데이터와 일치하는지 확인
        assertEquals(1500000, deletedDto.getCarrot());
        assertEquals("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..", deletedDto.getContent());
        assertEquals("Python", deletedDto.getUsedTechnique());

    }
}