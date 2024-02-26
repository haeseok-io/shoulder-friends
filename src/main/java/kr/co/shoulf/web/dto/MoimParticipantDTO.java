package kr.co.shoulf.web.dto;

import kr.co.shoulf.web.entity.MoimParticipants;
import kr.co.shoulf.web.entity.MoimParticipantsReject;
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
}
