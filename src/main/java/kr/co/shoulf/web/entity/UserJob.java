package kr.co.shoulf.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_job")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userJobNo;
    private Integer level;
    private Integer career;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_detail_no")
    @ToString.Exclude
    private PositionDetail positionDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    @ToString.Exclude
    @JsonIgnore
    private Users users;
}
