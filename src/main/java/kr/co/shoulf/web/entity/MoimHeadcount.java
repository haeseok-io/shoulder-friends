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
    private Integer personnel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_no", nullable = false)
    private Moim moim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_detail_no")
    private PositionDetail positionDetail;
}
