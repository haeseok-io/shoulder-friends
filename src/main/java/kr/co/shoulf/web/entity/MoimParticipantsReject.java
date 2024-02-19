package kr.co.shoulf.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "moim_participants_reject")
public class MoimParticipantsReject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimParticipantsRejectNo;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moimParticipantsNo", nullable = false)
    private MoimParticipants moimParticipantsNo;
    @Column(nullable = false)
    private String contents;
}
