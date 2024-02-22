package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member_salary_detail")
public class MemberSalaryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryDetailNo;
    @Column(nullable = false)
    private Integer paySal;
    private Integer payComm;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date payDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salary_no")
    private MemberSalary memberSalary;
}
