package kr.co.shoulf.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimDTO {
    private Long no;
    private String img;
    private String type;
    private String category;
    private String subject;
    private String desc;
    private Integer like;
    List<LanguageDTO> languageList;
    List<HeadcountDTO> headcountList;
}
