package kr.co.shoulf.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_online")
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
