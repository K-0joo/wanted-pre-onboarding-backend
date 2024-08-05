package wanted.recruit.employment.entity;
import jakarta.persistence.*;
import lombok.*;
import wanted.recruit.employment.dto.EmploymentDto;
import wanted.recruit.company.entity.Company;

@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
@ToString(exclude = "company") // 테스트 중 오류 발생하여 제외 함
@Entity
@Getter
@Setter
public class Employment {
    @Id // 대표키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    @Column(name = "employment_id")
    private Long id;

    // 해당 공고의 부모 게시글(회사)
    @ManyToOne // 공고 엔티티와 회사 엔티티를 다대일 관계로 설정
    @JoinColumn(name = "company_id") // 외래키 생성, Company 엔티티의 기본키(id)와 매핑
    private Company company; // 해당 공고의 부모 회사

    @Column(name = "employment_position")
    private String position;

    @Column(name = "employment_carrot")
    private int carrot;

    @Column(name = "employment_content")
    private String content;

    @Column(name = "used_technique")
    private String usedTechnique;

    public static Employment createEmployment(EmploymentDto dto, Company company) {
        // 예외 발생
        if(dto.getId() != null)
            throw new IllegalArgumentException("공고 생성 실패! 공고의 id가 없어야 합니다.");

        if(dto.getCompanyId() != company.getCompanyId())
            throw new IllegalArgumentException("공고 생성 실패! 회사의 id가 잘못됐습니다.");

        // 엔티티 생성 및 반환
        return new Employment(
                dto.getId(),
                company,
                dto.getPosition(),
                dto.getCarrot(),
                dto.getContent(),
                dto.getUsedTechnique()
        );
    }

    public void patch(EmploymentDto dto) {
       // 예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("공고 수정 실패! 잘못된 공고 id가 입력됐습니다.");

        // 객체 갱신
        if(dto.getPosition() != null)
            this.position = dto.getPosition();
        if(dto.getCarrot() != 0)
            this.carrot = dto.getCarrot();
        if(dto.getContent() != null)
            this.content = dto.getContent();
        if(dto.getUsedTechnique() != null)
            this.usedTechnique = dto.getUsedTechnique();
    }

}
