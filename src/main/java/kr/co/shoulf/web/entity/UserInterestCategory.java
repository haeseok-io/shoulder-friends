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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo", nullable = false)
    private UserDetail userDetail;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryNo", nullable = false)
    private Category category;
}
