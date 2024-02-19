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
    private Integer checklistNo;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "moimNo", nullable = false)
    private Moim moim;

//    @ManyToOne
//    @JoinColumn(name = "userNo", nullable = false)
//    private Users users;

}
