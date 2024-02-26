package kr.co.shoulf.web.dto;

import kr.co.shoulf.web.entity.UserPortfolio;
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
@EqualsAndHashCode(callSuper = false)
public class UserDetailDTO extends UserDTO {
    private String preferArea;
    private String gitLink;
    private String blogLink;
    private List<UserPortfolio> portfolioList;
    private List<LanguageDTO> languageList;
    private List<InterestDTO> interestList;
}
