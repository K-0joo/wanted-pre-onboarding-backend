package wanted.recruit.dto;

import lombok.*;
import wanted.recruit.entity.Employment;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CompanyForm {
    private String company_name;
    private String country;
    private String region;
}
