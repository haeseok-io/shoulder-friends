package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "meeting")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingNo;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp meetDate;

    @Column(nullable = false)
    private String addr;

    @Column
    private String contents;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp regdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moimNo", nullable = false)
    @ToString.Exclude
    private Moim moim;
}
