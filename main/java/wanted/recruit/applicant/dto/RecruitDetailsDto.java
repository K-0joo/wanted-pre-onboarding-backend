package wanted.recruit.applicant.dto;

import lombok.*;
import wanted.recruit.employment.entity.Employment;

import java.util.List;
import java.util.stream.Collectors;

// Detail에서 가져올 내용과 리스트 내용이 다르기 때문에 따로 추가 해줌.

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RecruitDetailsDto {
    private Long id;
    private String name;
    private String country;
    private String region;
    private String position;
    private int carrot;
    private String usedTechnique;

    private String content;
    private List<Long> otherEmploymentIds;

    public static RecruitDetailsDto detailEmployment(Employment employment){
        return new RecruitDetailsDto(
                employment.getId(),
                employment.getCompany().getName(),
                employment.getCompany().getCountry(),
                employment.getCompany().getRegion(),
                employment.getPosition(),
                employment.getCarrot(),
                employment.getUsedTechnique(),
                employment.getContent(),
                employment.getCompany().getEmployments().stream()
                        .filter(e -> !e.getId().equals(employment.getId()))
                        .map(Employment::getId)
                        .collect(Collectors.toList())
        );
    }
}
