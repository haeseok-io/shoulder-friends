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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = false)
    private UserDetail userDetail;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "online_no", nullable = false)
    private Online online;
}
