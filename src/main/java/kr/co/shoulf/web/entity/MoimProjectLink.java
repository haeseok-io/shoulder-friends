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
    private Long moimProjectLinkNo;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moimNo", nullable = false)
    private MoimDetail moimDetail;
}
