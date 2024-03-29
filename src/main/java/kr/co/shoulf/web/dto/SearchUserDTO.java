package kr.co.shoulf.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserDTO {
    private Integer positionNo;
    private Integer positionDetailNo;
    private Long onlineNo;
    private String keyword;
}
