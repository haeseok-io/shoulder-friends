package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member_annual")
public class MemberAnnual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annualNo;
    @Column(nullable = false)
    private Integer type;
    @Column(nullable = false)
    private Integer num;
    private String reason;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date issueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    @ToString.Exclude
    private Member member;
}
