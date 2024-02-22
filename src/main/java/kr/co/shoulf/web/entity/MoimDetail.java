package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "moim_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoimDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimNo;
    @Column(nullable = false)
    private String detailDesc;
    @Column(length = 200)
    private String moimImg;
    @Column
    private Integer fee;
    @Column(length = 200)
    private String offAddr;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "moim_no")
    @ToString.Exclude
    private Moim moim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "online_no", nullable = false)
    @ToString.Exclude
    private Online online;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catrgory_no")
    @ToString.Exclude
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_category_no")
    @ToString.Exclude
    private StudyCategory studyCategory;


}
