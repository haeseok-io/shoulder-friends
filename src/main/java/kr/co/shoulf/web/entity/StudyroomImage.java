package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "studyroom_no")
    private StudyroomDetail studyroomDetail;
}
