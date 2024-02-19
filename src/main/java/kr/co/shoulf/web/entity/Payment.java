package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentNo;
    @Column(nullable = false)
    private Integer approvalNum;
    @Column(nullable = false)
    private Integer cardNum;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, length = 200)
    private String productName;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false)
    private Integer price;
    @ColumnDefault("1")
    private Integer status;

    @Column(nullable = false)
    private LocalDateTime payDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_no")
    private Reservation reservation;

}
