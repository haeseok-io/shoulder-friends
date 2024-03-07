package kr.co.shoulf.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimListRequestDTO {
    private String type;
    private Integer positionNo;
    private Integer positionDetailNo;
    private String keyword;
    private PageRequestDTO pageRequestDTO;
}
