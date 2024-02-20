package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "studyroom")
public class Studyroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyroomNo;
    @Column(length = 200,nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "studycafe_no", nullable = false)
    private Studycafe studycafe;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private StudyroomDetail studyroomDetail;
}
