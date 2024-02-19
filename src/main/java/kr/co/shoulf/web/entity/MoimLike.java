package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moim_like")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoimLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likeNo;

//    @ManyToOne
//    @JoinColumn(name = "user_no", nullable = false)
//    private Users user;

    @ManyToOne
    @JoinColumn(name = "moimNo", nullable = false)
    private Moim moim;
}
