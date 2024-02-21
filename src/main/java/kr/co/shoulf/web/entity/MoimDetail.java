package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Moim moim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "online_no", nullable = false)
    private Online online;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catrgory_no")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_category_no")
    private StudyCategory studyCategory;


}
