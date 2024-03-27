package kr.co.shoulf.web.dto;

import kr.co.shoulf.web.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MoimParticipantDTO extends MoimParticipants {
    private MoimParticipantsReject reject;
    private UserJob userJob;
    private UserOnline userOnline;
    private Long moimParticipantsNo;
    private Integer status;
    private String reason;
    private String job;
    private Users users;
    private MoimHeadcount moimHeadcount;
}
