package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "studyroom_detail")
public class StudyroomDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyroomNo;
    @Column(nullable = false, columnDefinition = "int DEFAULT 1")
    private Integer maxNum;
    @Column(length = 2, columnDefinition = "char(2) default 'N'")
    private String beam;
    @Column(length = 2, columnDefinition = "char(2) default 'N'")
    private String wboard;
    @Column
    private Integer socket;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "studyroom_no")
    @ToString.Exclude
    private Studyroom studyroom;
}
