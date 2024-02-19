package kr.co.shoulf.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_interest_category")
public class UserInterestCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInterestCategoryNo;
    @ManyToOne
    @JoinColumn(name = "userNo", nullable = false)
    private UserDetail userNo;
    @ManyToOne
    @JoinColumn(name = "categoryNo", nullable = false)
    private Category categoryNo;
}
