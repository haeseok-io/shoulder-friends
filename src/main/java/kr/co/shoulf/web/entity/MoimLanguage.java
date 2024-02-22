package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "moim_language")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoimLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimLanguageNo;
    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_no", nullable = false)
    @ToString.Exclude
    private Moim moim;
}
