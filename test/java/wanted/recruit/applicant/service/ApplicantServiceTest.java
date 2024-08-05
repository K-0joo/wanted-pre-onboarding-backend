package wanted.recruit.applicant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wanted.recruit.company.entity.Company;
import wanted.recruit.employment.entity.Employment;

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
    @Test
    void findAllEmployments() {
        // 1. 예상 데이터
        Company company = new Company();
        company.setCompanyId(1L);
        company.setName("원티드랩");
        company.setCountry("한국");
        company.setRegion("서울");

        // 현재 데이터 회사 아이디 다른 데이터를 넣으니.. 안됨... 어떻게 테스트 케이스를 만들어야 하지...?
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

        // 현재 데이터 회사 아이디 다른 데이터를 넣으니.. 안됨... 어떻게 테스트 케이스를 만들어야 하지...?
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
        // 원래는 null 처리로 해결해야 하는데 시간이 부족한 관계로..
        Long id = -1L;
        Employment expected = null;

        // 2. 실제 데이터
        Employment employment = applicantService.detailEmployment(id);

        // 3. 비교 및 검증
        assertEquals(expected, employment);

        // 만약 log로 "채용 공고를 찾을 수 없습니다!"가 뜬다면 테스트 성공
    }
}