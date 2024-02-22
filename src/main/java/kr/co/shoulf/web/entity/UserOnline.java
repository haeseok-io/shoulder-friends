package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_online")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOnline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userOnlineNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = false)
    @ToString.Exclude
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "online_no", nullable = false)
    @ToString.Exclude
    private Online online;
}
