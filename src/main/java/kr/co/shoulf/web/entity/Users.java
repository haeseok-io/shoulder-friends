package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 100)
    private String pass;
    @Column(length = 100)
    private String nickname;
    @Column(length = 20, columnDefinition = "varchar(20) default 'ROLE_USER'")
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserDetail userDetail;
}
