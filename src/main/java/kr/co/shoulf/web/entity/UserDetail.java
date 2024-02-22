package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;
    @Column
    private String introduce;
    @Column(length = 100)
    private String preferArea;
    @Column
    private String profileImg;
    @Column
    private String gitLink;
    @Column
    private String blogLink;


    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_no")
    @ToString.Exclude
    private Users users;
}
