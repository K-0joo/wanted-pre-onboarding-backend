package wanted.recruit.applicant.dto;

import lombok.*;
import wanted.recruit.employment.entity.Employment;

// 전체 리스트 조회 DTO

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RecruitDto {
    private Long id;
    private String name;
    private String country;
    private String region;
    private String position;
    private int carrot;
    private String usedTechnique;


    public static RecruitDto allEmployments(Employment employment){
        return new RecruitDto(
                employment.getId(),
                employment.getCompany().getName(),
                employment.getCompany().getCountry(),
                employment.getCompany().getRegion(),
                employment.getPosition(),
                employment.getCarrot(),
                employment.getUsedTechnique()
        );
    }

}
