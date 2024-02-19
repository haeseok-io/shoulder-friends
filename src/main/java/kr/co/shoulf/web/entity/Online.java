package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "online")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Online {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long onlineNo;
    @Column(length = 100, nullable = false)
    private String onlineName;
}
