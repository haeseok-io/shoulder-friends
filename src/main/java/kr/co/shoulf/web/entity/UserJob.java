package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_job")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;
    private Integer level;
    private Integer career;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_detail_no", nullable = false)
    private PositionDetail positionDetail;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private UserDetail userDetail;
}
