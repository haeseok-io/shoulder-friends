package kr.co.shoulf.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "moim_participants")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class MoimParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimParticipantsNo;
    @Column(columnDefinition = "LONGTEXT")
    private String reason;
    @Column(length = 20)
    private String job;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = false)
    @ToString.Exclude
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_headcount_no", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private MoimHeadcount moimHeadcount;
}
