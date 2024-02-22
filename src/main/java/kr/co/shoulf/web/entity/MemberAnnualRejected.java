package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member_annual_rejected")
public class MemberAnnualRejected {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  annualRejectedNo;

    @Column(length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reject_member_no")
    @ToString.Exclude
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "annual_detail_no")
    @ToString.Exclude
    MemberAnnualDetail memberAnnualDetail;
}
