package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "position_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PositionDetailNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Position_no", nullable = false)
    private Position position;
    @Column(length = 100, nullable = false)
    private String middleName;
}
