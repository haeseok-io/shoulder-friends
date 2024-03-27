package kr.co.shoulf.web.dto;

import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.UserJob;
import kr.co.shoulf.web.entity.Users;
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
public class FindUserDTO extends Users {
    private List<Moim> moim;
    private UserJob userJob;
}
