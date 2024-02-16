package kr.co.shoulf.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "studyroom_detail")
public class StudyroomDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyroomNo;
    @Column(nullable = false, columnDefinition = "int DEFAULT 1")
    private Integer maxNum;
    @Column(length = 2, columnDefinition = "char(2) default 'N'")
    private String beam;
    @Column(length = 2, columnDefinition = "char(2) default 'N'")
    private String wboard;

}
