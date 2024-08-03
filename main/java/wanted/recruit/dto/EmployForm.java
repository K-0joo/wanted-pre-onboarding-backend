package wanted.recruit.dto;

import lombok.*;
import wanted.recruit.entity.Company;
import wanted.recruit.entity.Employment;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EmployForm {

    private Long employment_id;
    private Company company;
    private String employment_position;
    private int employment_carrot;
    private String employment_content;
    private String used_technique;

    // 전송받은 제목과 내용을 필드에 저장하는 생성자 추가

    public Employment toEntity() {
        return new Employment(employment_id, null, employment_position, employment_carrot, employment_content, used_technique);
    }

}
