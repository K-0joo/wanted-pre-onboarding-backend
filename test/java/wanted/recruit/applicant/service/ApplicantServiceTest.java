package wanted.recruit.applicant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wanted.recruit.employment.entity.Employment;

// 앞으로 사용할 클래스 패키지를 예비로 임포트
// File -> Settings -> Editor -> General -> Auto Import 선택하기
// Optimize imports on the fly 체크 해제
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스를 스프링 부트와 연동해 테스트
class ApplicantServiceTest {

    @Autowired
    ApplicantService applicantService; //applicantService 객체 주입
    @Test
    void findAllEmployments() {
        // 1. 예상 데이터

        // 2. 실제 데이터
        List<Employment> employments = applicantService.findAllEmployments();

        // 3. 비교 및 검증
    }
}