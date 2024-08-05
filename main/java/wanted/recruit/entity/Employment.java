package wanted.recruit.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import wanted.recruit.dto.EmploymentDto;

@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
@ToString
@Entity
@Getter
public class Employment {
    @Id // 대표키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long employment_id;

    // 해당 공고의 부모 게시글(회사)
    @ManyToOne // 공고 엔티티와 회사 엔티티를 다대일 관계로 설정
    @JoinColumn(name = "company_id") // 외래키 생성, Company 엔티티의 기본키(id)와 매핑
    private Company company; // 해당 공고의 부모 회사

    @Column
    private String employment_position;

    @Column
    private int employment_carrot;

    @Column
    private String employment_content;

    @Column
    private String used_technique;

    public static Employment createEmployment(EmploymentDto dto, Company company) {
        // 예외 발생
        if(dto.getEmployment_id() != null)
            throw new IllegalArgumentException("공고 생성 실패! 공고의 id가 없어야 합니다.");

        if(dto.getCompany_id() != company.getCompany_id())
            throw new IllegalArgumentException("공고 생성 실패! 회사의 id가 잘못됐습니다.");

        // 엔티티 생성 및 반환
        return new Employment(
                dto.getEmployment_id(),
                company,
                dto.getEmployment_position(),
                dto.getEmployment_carrot(),
                dto.getEmployment_content(),
                dto.getUsed_technique()
        );
    }

    public void patch(Employment employment) {
        if(employment.employment_position != null)
            this.employment_position = employment.employment_position;
        if(employment.employment_carrot != 0) {
            this.employment_carrot = employment.employment_carrot;
        }
        if(employment.employment_content != null){
            this.employment_content = employment.employment_content;
        }
        if(employment.used_technique != null){
            this.used_technique = employment.used_technique;
        }
    }

}
