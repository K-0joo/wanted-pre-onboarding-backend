package wanted.recruit.employment.dto;

import lombok.*;
import wanted.recruit.employment.entity.Employment;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EmploymentDto {

    private Long id;
    private Long companyId;
    private String position;
    private int carrot;
    private String content;
    private String usedTechnique;

    public static EmploymentDto createEmploymentDto(Employment employment) {
        return new EmploymentDto(
                employment.getId(),
                employment.getCompany().getCompanyId(),
                employment.getPosition(),
                employment.getCarrot(),
                employment.getContent(),
                employment.getUsedTechnique()
        );
    }
}
