package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no", nullable = false)
    private Category category;
}
