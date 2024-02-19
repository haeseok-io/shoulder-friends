package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commuteNo;
    @UpdateTimestamp
    @Temporal(TemporalType.TIME)
    private String inTime;
    @UpdateTimestamp
    @Temporal(TemporalType.TIME)
    private String outTime;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private String date;
    @Column(length = 20, nullable = false)
    private String status;
}
