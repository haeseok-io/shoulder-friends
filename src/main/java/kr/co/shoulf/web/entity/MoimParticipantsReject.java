package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "moim_participants_no", nullable = false)
    @ToString.Exclude
    private MoimParticipants moimParticipants;

    @Column(nullable = false)
    private String contents;
}
