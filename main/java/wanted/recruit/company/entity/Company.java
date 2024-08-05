package wanted.recruit.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import wanted.recruit.employment.entity.Employment;

import java.util.List;

@AllArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="company_id")
    private Long companyId;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
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