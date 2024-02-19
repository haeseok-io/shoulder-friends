package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "study_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyCategoryNo;
    @Column(length = 100, nullable = false)
    private String studyCategoryName;
}
