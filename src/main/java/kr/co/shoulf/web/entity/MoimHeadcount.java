package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moim_headcount")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
