package kr.co.shoulf.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataRequestDTO {
    private Long userNo;
    private MultipartFile profileImg;
    private String nickname;
    private String introduce;
    private Long userJobNo;
    private Integer positionDetailNo;
    private Integer level;
    private Integer career;
    private Long userOnlineNo;
    private Long onlineNo;
    private String preferArea;
    private List<Long> categoryNo;
    private List<Long> portfolioNo;
    private List<String> portfolio;
    private String gitLink;
    private String blogLink;
}
