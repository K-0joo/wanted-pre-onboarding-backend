package wanted.recruit.applicant.dto;

import lombok.*;
import wanted.recruit.applicant.entity.Applicant;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ApplicantDto {
    private Long applicantId;
    private Long employmentId;
    private Long userId;

    public static ApplicantDto createApplyDto(Applicant applicant) {
        return new ApplicantDto(
                applicant.getApplicantId(),
                applicant.getEmployment().getId(),
                applicant.getUserId()
        );
    }
}
