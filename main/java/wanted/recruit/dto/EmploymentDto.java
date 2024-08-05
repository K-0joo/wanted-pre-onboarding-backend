package wanted.recruit.dto;

import lombok.*;
import wanted.recruit.entity.Company;
import wanted.recruit.entity.Employment;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EmploymentDto {

    private Long employment_id;
    private Long company_id;
    private String employment_position;
    private int employment_carrot;
    private String employment_content;
    private String used_technique;

    public static EmploymentDto createEmploymentDto(Employment employment) {
        return new EmploymentDto(
                employment.getEmployment_id(),
                employment.getCompany().getCompany_id(),
                employment.getEmployment_position(),
                employment.getEmployment_carrot(),
                employment.getEmployment_content(),
                employment.getUsed_technique()
        );
    }

    // 전송받은 제목과 내용을 필드에 저장하는 생성자 추가

    public Employment toEntity() {
        return new Employment(employment_id, null, employment_position, employment_carrot, employment_content, used_technique);
    }

}
