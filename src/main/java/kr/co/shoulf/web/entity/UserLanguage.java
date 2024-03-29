package kr.co.shoulf.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_language")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLanguageNo;
    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    @ToString.Exclude
    @JsonIgnore
    private Users users;
}
