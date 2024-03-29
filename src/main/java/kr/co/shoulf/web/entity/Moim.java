package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Table(name = "moim")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DynamicInsert
@DynamicUpdate
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

    @Column
    @ColumnDefault("1") // 기본값
    private Integer status;

    @Column
    @ColumnDefault("0") // 기본값
    private Integer hits;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private MoimDetail moimDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = false)
    @ToString.Exclude
    private Users users;

    @OneToMany(mappedBy = "moim", fetch = FetchType.LAZY)
    private List<MoimHeadcount> moimHeadcountList;

    @OneToMany(mappedBy = "moim", fetch = FetchType.LAZY)
    private List<MoimLanguage> moimLanguageList;
}


