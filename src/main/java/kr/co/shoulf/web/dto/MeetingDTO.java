package kr.co.shoulf.web.dto;

import kr.co.shoulf.web.entity.Moim;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDTO {
    private String title;
    private String startDate;
    private String  endDate;
    private String addr;
    private Long studycafeNo;
    private Long studyroomNo;
    private Integer price;
    private String startTime;
    private Long moimNo;
}
