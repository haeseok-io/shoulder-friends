package kr.co.shoulf.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "position_detail")
public class PositionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobDetailNo;
    @ManyToOne
    @JoinColumn(name = "jobNo", nullable = false)
    private Position jobNo;
    @Column(length = 100, nullable = false)
    private String middleName;
}
