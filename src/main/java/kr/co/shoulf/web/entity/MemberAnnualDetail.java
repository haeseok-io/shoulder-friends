package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
