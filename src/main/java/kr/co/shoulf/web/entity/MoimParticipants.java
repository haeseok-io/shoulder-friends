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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo", nullable = false)
    private Users users;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "moimHeadcountNo", nullable = false)
    private MoimHeadcount moimHeadcount;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "moimNo", nullable = false)
    private Moim moim;
}
