package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Entity
@Table(name = "board")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;
    @Column(nullable = false)
    private Integer cate;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String contents;
    @Column(nullable = false, length = 20)
    private String ip;
    @Column
    @ColumnDefault("0") // 기본값
    private Integer hits;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regdate", updatable = false)
    private Timestamp regdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    @ToString.Exclude
    private Users users;
}
