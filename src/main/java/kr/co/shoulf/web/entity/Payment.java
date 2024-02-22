package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;

@Entity
@Table(name = "payment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentNo;
    @Column(nullable = false)
    private Long approvalNum;
    @Column(nullable = false)
    private Long cardNum;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, length = 200)
    private String productName;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false)
    private Integer price;
    @Column
    @ColumnDefault("1")
    private Integer status;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date payDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_no")
    @ToString.Exclude
    private Reservation reservation;

}
