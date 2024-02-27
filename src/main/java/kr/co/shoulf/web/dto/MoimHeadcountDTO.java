package kr.co.shoulf.web.dto;

import kr.co.shoulf.web.entity.MoimHeadcount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MoimHeadcountDTO extends MoimHeadcount {
    private List<MoimParticipantDTO> participantList;
    private List<MoimParticipantDTO> participantApprovalList;
    private List<MoimParticipantDTO> participantRejectedList;
}
