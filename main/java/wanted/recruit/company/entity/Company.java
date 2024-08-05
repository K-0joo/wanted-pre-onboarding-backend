package wanted.recruit.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import wanted.recruit.employment.entity.Employment;

import java.util.List;

@AllArgsConstructor
@ToString(exclude = "employments") // employments 필드를 제외하고 toString() 생성
@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="company_id")
    private Long companyId;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<Employment> employments;

    @Column(name = "company_name")
    private String name;

    @Column
    private String country;

    @Column
    private String region;

    public Company() {
    }


}