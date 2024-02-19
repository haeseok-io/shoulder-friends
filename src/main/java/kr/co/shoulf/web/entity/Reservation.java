package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationNo;

    @Column(nullable = false)
    private LocalDate checkin;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regdate;

    @ColumnDefault("1")
    private Integer status;

    @Column(length = 20)
    private String ip;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private Users users;

    // 일정과 공간에 대한 설정 해야함
}
