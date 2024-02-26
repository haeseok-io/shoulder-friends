package kr.co.shoulf.web.dto;

import kr.co.shoulf.web.entity.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MoimDTO extends Moim {
    private List<LanguageDTO> languageList; // 사용언어/기술
    private List<MoimHeadcountDTO> headcountList; // 모집인원
    private List<MoimLike> likeList; // 관심
    private List<MoimProjectLink> projectLinkList; // 참고링크
    private List<MoimProjectPlatform> projectPlatformList; // 플랫폼
    private List<Checklist> checklist; // 체크리스트
}
