package wanted.recruit.dto;

import lombok.*;
import wanted.recruit.entity.Employment;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RecruitDto {
    private Long employment_id;
    private String company_name;
    private String country;
    private String region;
    private String employment_position;
    private int employment_carrot;
    private String used_technique;

    public static RecruitDto AllEmploymentList(Employment employment){
        return new RecruitDto(
                employment.getEmployment_id(),
                employment.getCompany().getCompany_name(),
                employment.getCompany().getCountry(),
                employment.getCompany().getRegion(),
                employment.getEmployment_position(),
                employment.getEmployment_carrot(),
                employment.getUsed_technique()
        );
    }
}
