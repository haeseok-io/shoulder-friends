package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member_commute")
public class MemberCommute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commuteNo;
    @Temporal(TemporalType.TIME)
    private Time inTime;
    @Temporal(TemporalType.TIME)
    private Time outTime;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;
    @Column(length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    @ToString.Exclude
    private Member member;
}
