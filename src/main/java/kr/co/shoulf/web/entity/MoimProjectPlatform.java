package kr.co.shoulf.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @ToString.Exclude
    private Platform platform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_no", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Moim moim;
}
