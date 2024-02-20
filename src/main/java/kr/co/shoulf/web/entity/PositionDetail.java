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
    private Long jobDetailNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_no", nullable = false)
    private Position position;
    @Column(length = 100, nullable = false)
    private String middleName;
}
