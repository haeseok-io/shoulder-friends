package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "studycafe")
public class Studycafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studycafeNo;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 200)
    private String shortDesc;
    @Column(length = 20, nullable = false)
    private String post;
    @Column(length = 200, nullable = false)
    private String addr;
    @Column(length = 200)
    private String mainImg;
    @Column(nullable = false)
    private Double x;
    @Column(nullable = false)
    private Double y;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    @ToString.Exclude
    private Member member;
}
