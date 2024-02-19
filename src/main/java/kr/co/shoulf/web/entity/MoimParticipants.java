package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import org.apache.catalina.User;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Entity
@Table(name = "moim_participants")
public class MoimParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimParticipantsNo;
    @Column(columnDefinition = "LONGTEXT")
    private String reason;
    @Column(length = 20)
    private String job;
    @Column(nullable = false)
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "userNo", nullable = false)
    private Users userNo;
    @ManyToOne
    @JoinColumn(name = "moimHeadcountNo", nullable = false)
    private MoimHeadcount moimHeadcountNo;
    @ManyToOne
    @JoinColumn(name = "moimNo", nullable = false)
    private Moim moimNo;
}
