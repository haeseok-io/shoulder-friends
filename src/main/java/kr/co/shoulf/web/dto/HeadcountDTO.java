package kr.co.shoulf.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeadcountDTO {
    private Long no;
    private int personnel;
    private String positionName;
    private String positionDetailName;
    private int approvalNum; // 승인자수
}
