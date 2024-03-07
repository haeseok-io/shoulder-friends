package kr.co.shoulf.web.dto;

import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.Studyroom;
import kr.co.shoulf.web.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservPaymentDTO {
    private Long approvalNum;
    private Long cardNum;
    private String name;
    private String productName;
    private String email;
    private Integer price;
    private String checkin;
    private Long userNo;
    private Long moimNo;
    private Long studyroomNo;
    private String title;
    private String startDate;
    private String  endDate;
    private String addr;
    private String startTime;
}
