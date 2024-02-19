package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "meeting")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer meetingNo;

    @Column(nullable = false)
    private LocalDateTime meetDate;

    @Column(nullable = false)
    private String addr;

    @Column
    private String contents;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime regdate;

    @ManyToOne
    @JoinColumn(name = "moimNo", nullable = false)
    private Moim moim;
}
