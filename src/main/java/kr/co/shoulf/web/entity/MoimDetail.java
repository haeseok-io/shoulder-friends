package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moim_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoimDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moimNo;
    @Column(nullable = false)
    private String desc;
    @Column(length = 200)
    private String moimImg;
    @Column
    private Integer fee;
    @Column(length = 200)
    private String offAddr;

    @ManyToOne
    @JoinColumn(name = "onlineNo",nullable = false)
    private Online online;

    @ManyToOne
    @JoinColumn(name = "catrgoryNo")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "studyCategoryNo")
    private  StudyCategory studyCategory;



}
