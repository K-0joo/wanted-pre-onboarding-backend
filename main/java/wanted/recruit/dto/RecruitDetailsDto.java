package wanted.recruit.dto;

import lombok.*;
import wanted.recruit.entity.Employment;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RecruitDetailsDto {
    private Long employment_id;
    private String company_name;
    private String country;
    private String region;
    private String employment_position;
    private int employment_carrot;
    private String used_technique;

    private String employment_content;
    private List<Long> other_employment_ids;

    public static RecruitDetailsDto detailEmployment(Employment employment){
        return new RecruitDetailsDto(
                employment.getEmployment_id(),
                employment.getCompany().getCompany_name(),
                employment.getCompany().getCountry(),
                employment.getCompany().getRegion(),
                employment.getEmployment_position(),
                employment.getEmployment_carrot(),
                employment.getUsed_technique(),
                employment.getEmployment_content(),
                employment.getCompany().getEmployments().stream()
                        .filter(e -> !e.getEmployment_id().equals(employment.getEmployment_id()))
                        .map(Employment::getEmployment_id)
                        .collect(Collectors.toList())
        );
    }
}
