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
@Table(name = "member_salary")
public class MemberSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryNo;
    @Column(nullable = false)
    private Integer sal;
}
