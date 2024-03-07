package kr.co.shoulf.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "moim_like")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoimLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = false)
    @ToString.Exclude
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_no", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Moim moim;
}
