package wanted.recruit.applicant.entity;

import jakarta.persistence.*;
import lombok.*;
import wanted.recruit.applicant.dto.ApplicantDto;
import wanted.recruit.employment.entity.Employment;

@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
@ToString(exclude = "employment") // 테스트 중 오류 발생하여 제외 함
@Entity
@Getter
@Setter
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="applicant_id")
    private Long applicantId;

    @ManyToOne
    @JoinColumn(name="employment_id")
    private Employment employment;

    @Column(name = "user_id")
    private Long userId;

    public static Applicant createApply(ApplicantDto dto, Employment employment) {
        // 예외 발생
        if(dto.getEmploymentId() == null)
            throw new IllegalArgumentException("공고 지원 실패! 공고 ID가 필요합니다.");

        if(dto.getEmploymentId() != employment.getId())
            throw new IllegalArgumentException("공고 지원 실패! 공고의 id가 잘못됐습니다.");

        // 엔티티 생성 및 반환
        return new Applicant(
                dto.getApplicantId(),
                employment,
                dto.getUserId()
        );
    }
}
