package kr.co.shoulf.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "online")
public class Online {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long onlineNo;
    @Column(length = 100, nullable = false)
    private String onlineName;
}
