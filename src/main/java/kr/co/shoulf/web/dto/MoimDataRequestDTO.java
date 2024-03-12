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
public class MoimDataRequestDTO {
    private Long moimNo;
    private String type;
    private String subject;
    private String shortDesc;
    private String detailDesc;
    private MultipartFile moimImg;
    private String moimImgPath;
    private Long categoryNo;
    private Long studyCategoryNo;
    private List<Integer> platformNo;
    private Long onlineNo;
    private String offAddr;
    private Integer fee;
    private List<Long> headcountNo;
    private List<Integer> positionNo;
    private List<Integer> positionDetailNo;
    private List<Integer> personnel;
    private List<String> language;
    private List<Long> linkNo;
    private List<String> link;
}
