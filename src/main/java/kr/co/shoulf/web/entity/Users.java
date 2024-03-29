package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
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
    @Column
    private String oauth2Where;

    @Column(name = "last_login_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastLoginDate;

    @Column(length = 50)
    @ColumnDefault("'ROLE_USER'")
    private String role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private UserDetail userDetail;

    @OneToOne(mappedBy = "users", fetch = FetchType.LAZY)
    private UserJob userJob;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<UserLanguage> userLanguageList;
}
