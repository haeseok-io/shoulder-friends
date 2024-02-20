package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moim_participants_reject")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimParticipantsReject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimParticipantsRejectNo;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "moim_participants_no", nullable = false)
    private MoimParticipants moimParticipants;
    @Column(nullable = false)
    private String contents;
}
