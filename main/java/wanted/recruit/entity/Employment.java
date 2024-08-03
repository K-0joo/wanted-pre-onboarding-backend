package wanted.recruit.entity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 추가 어조테이션
@ToString
@Entity
@Getter
@Setter
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employment_id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column
    private String employment_position;

    @Column
    private int employment_carrot;

    @Column
    private String employment_content;

    @Column
    private String used_technique;

}
