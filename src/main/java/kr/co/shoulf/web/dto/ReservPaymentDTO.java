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
    private String studycafename;
    private String email;
    private Integer price;
    private String checkin;
    private Long userNo;
    private Long moimNo;
    private Long studyroomNo;
    private String calendar_content;
    private String calendar_start_date;
    private String  endDate;
    private String calendar_addr;
    private String start_time;
    private Long amount;
}
