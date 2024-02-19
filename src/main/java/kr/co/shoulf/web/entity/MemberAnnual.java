package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    private String issueDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_no")
    @Column(nullable = false)
    private Member member;
}
