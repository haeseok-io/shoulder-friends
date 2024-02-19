package kr.co.shoulf.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "moim_headcount")
public class MoimHeadcount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimHeadcountNo;
    @ManyToOne
    @JoinColumn(name = "moimNo", nullable = false)
    private Moim moimNo;
    private Integer personnel;
    @ManyToOne
    @JoinColumn(name = "jobDetailNo")
    private PositionDetail jobDetailNo;
}
