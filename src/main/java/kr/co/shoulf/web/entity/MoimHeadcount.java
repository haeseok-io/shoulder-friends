package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "moim_headcount")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MoimHeadcount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimHeadcountNo;
    private Integer personnel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_no", nullable = false)
    @ToString.Exclude
    private Moim moim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_detail_no")
    @ToString.Exclude
    private PositionDetail positionDetail;
}
