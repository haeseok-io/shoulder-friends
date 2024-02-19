package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "checklist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checklistNo;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Integer status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "moimNo", nullable = false)
    private Moim moim;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo", nullable = false)
    private Users users;

}
