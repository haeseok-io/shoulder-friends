package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageNo;
    @Column(nullable = false)
    private Long groupNo;
    @Column(nullable = false)
    private String contents;
    @Column
    @ColumnDefault("1")
    private Integer status;
    @Column(nullable = false, length = 20)
    private String ip;

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regdate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_no")
    private Users receiver;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_no")
    private Users sender;
}
