package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moim_project_paltform")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoimProjectPlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimProjectPlaformNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platformNo", nullable = false)
    private Platform platform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_no", nullable = false)
    private Moim moim;
}
