package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Entity
@Table(name = "moim_participants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = false)
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_headcount_no", nullable = false)
    private MoimHeadcount moimHeadcount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_no", nullable = false)
    private Moim moim;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MoimParticipantsReject moimParticipantsReject;
}
