package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "platform")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer platformNo;

    @Column(nullable = false, length = 100)
    private String name;
}
