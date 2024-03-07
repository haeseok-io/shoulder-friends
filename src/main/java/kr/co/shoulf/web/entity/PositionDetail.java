package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "position_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer positionDetailNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_no", nullable = false)
    @ToString.Exclude
    private Position position;

    @Column(length = 100, nullable = false)
    private String middleName;
}
