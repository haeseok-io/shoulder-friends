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
    @ManyToOne
    @JoinColumn(name = "jobDetailNo", nullable = false)
    private PositionDetail jobDetailNo;
}
