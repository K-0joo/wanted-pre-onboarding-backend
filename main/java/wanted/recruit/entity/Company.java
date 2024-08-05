package wanted.recruit.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long company_id;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private List<Employment> employments;

    @Column
    private String company_name;

    @Column
    private String country;

    @Column
    private String region;

    public Company() {
    }


}