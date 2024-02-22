package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_interest_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInterestCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInterestCategoryNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = false)
    @ToString.Exclude
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no", nullable = false)
    @ToString.Exclude
    private Category category;
}
