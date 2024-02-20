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
@Table(name = "studycafe_detail")
public class StudycafeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studycafeNo;
    @Column(nullable = false)
    private String detail;
    @Column(nullable = false)
    private Integer x;
    @Column(nullable = false)
    private Integer Y;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "studycafe_no")
    private Studycafe studycafe;

}
