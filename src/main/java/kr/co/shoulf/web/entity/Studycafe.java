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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_no")
    private Member member;
}
