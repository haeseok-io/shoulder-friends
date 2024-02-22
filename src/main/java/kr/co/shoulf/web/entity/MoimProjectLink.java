package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "moim_project_link")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoimProjectLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimProjectLinkNo;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_no", nullable = false)
    @ToString.Exclude
    private Moim moim;
}
