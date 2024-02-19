package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moim_language")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoimLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moimLanguageNo;
    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "moimNo", nullable = false)
    private Moim moim;
}
