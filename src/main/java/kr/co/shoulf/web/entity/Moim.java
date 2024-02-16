package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "moim")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Moim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimNo;
    @Column(nullable = false, length = 20)
    private String type;
    @Column(nullable = false)
    private String subject;
    @Column
    private String shortDesc;
    @Column(nullable = false)
    @ColumnDefault("1") // 기본값
    private Integer status;

    @Column
    private Integer hits;

    //    @ManyToOne
    //    @JoinColumn(name = "userNo", nullable = false)
    //    private Users users;
}


