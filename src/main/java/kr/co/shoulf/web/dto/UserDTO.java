package kr.co.shoulf.web.dto;

import kr.co.shoulf.web.entity.*;
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
public class UserDTO extends Users {
    private UserJob userJob;
    private List<UserPortfolio> portfolioList;
    private List<LanguageDTO> languageList;
    private List<UserInterestCategory> interestCategoryList;
    private UserOnline userOnline;
    private List<MoimLike> moimLikeList;

    private List<MoimDTO> progressMoim; // 진행중인 모임

}
