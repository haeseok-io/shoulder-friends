package kr.co.shoulf.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userNo;
    private String nickname;
    private String profileImg;
    private String introduce;
    private Integer positionLevel;
    private String positionDetailName;
    private String online;

    private Integer progressMoimNum; // 진행중인 모임 갯수
}
