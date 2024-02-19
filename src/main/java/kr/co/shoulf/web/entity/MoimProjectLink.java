package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moim_project_link")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoimProjectLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moimProjectLinkNo;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "moimNo", nullable = false)
    private Moim moim;
}
