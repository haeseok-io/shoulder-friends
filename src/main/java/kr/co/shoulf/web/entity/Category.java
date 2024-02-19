package kr.co.shoulf.web.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryNo;
    @Column(length = 100, nullable = false)
    private String categoryName;

}
