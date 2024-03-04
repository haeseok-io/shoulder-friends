package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Entity
@Table(name = "message")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageNo;
    @Column(nullable = false)
    private Long groupNo;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String contents;
    @Column
    @ColumnDefault("1")
    private Integer status;
    @Column(nullable = false, length = 20)
    private String ip;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regdate", updatable = false)
    private Timestamp regdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_no")
    private Users receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_no")
    private Users sender;
}
