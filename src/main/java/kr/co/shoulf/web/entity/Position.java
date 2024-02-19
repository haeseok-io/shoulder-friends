package kr.co.shoulf.web.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobNo;
    @Column(length = 100, nullable = false)
    private String bigName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "jobNo")
    private List<PositionDetail> positionDetailList;
}
