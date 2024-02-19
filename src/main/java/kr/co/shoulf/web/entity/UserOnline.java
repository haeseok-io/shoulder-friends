package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "userNo", nullable = false)
    private UserDetail userNo;
    @ManyToOne
    @JoinColumn(name = "onlineNo", nullable = false)
    private Online onlineNo;
}
