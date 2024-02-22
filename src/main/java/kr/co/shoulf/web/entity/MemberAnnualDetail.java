package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member_annual_detail")
public class MemberAnnualDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annualDetailNo;
    @Column(nullable = false)
    private Integer useCate;
    @Column(nullable = false)
    private String startDate;
    @Column(nullable = false)
    private String endDate;
    @Column(nullable = false)
    private Integer useNum;
    @Column(nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    @ToString.Exclude
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_member_no")
    @ToString.Exclude
    private Member approvalMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "annual_no")
    @ToString.Exclude
    private MemberAnnual memberAnnual;
}
