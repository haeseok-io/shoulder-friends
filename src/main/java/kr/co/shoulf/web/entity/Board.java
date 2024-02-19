package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;
    @Column(nullable = false)
    private Integer cate;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false, length = 20)
    private String ip;

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regdate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private Users users;
}
