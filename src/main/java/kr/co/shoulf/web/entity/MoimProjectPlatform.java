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
    private Integer moimProjectPlaformNo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "platformNo", nullable = false)
    private Platform platform;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "moimNo", nullable = false)
    private Moim moim;
}
