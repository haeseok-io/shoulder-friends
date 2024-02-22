package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_portfolio")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPortfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioNo;
    @Column
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    @ToString.Exclude
    private Users users;
}
