package kr.co.shoulf.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    @JsonIgnore
    private Moim moim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_detail_no")
    @ToString.Exclude
    private PositionDetail positionDetail;

    @OneToMany(mappedBy = "moimHeadcount", fetch = FetchType.LAZY)
    private List<MoimParticipants> moimParticipantsList;
}
