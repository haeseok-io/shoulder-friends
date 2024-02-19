package kr.co.shoulf.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_job")
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
