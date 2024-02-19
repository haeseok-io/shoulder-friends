package kr.co.shoulf.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "study_category")
public class StudyCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyCategoryNo;
    @Column(length = 100, nullable = false)
    private String studyCategoryName;
}
