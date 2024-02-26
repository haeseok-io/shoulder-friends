package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "studyroom_image")
public class StudyroomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyroomImageNo;
    @Column(length = 200, nullable = false)
    private String path;
    @Column(nullable = false)
    private Integer seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studyroom_no")
    @ToString.Exclude
    private Studyroom studyroom;
}
