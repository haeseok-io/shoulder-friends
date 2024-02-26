package kr.co.shoulf.web.service;

import jakarta.transaction.Transactional;
import kr.co.shoulf.web.dto.*;
import kr.co.shoulf.web.entity.*;
import kr.co.shoulf.web.repository.*;
import kr.co.shoulf.web.util.LanguageImgPathConvert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoimService {
    private final MoimRepository moimRepository;
    private final MoimLikeRepository moimLikeRepository;
    private final MoimLanguageRepository moimLanguageRepository;
    private final MoimHeadcountRepository moimHeadcountRepository;
    private final MoimParticipantsRepository moimParticipantsRepository;

    private final StudycafeRepository studycafeRepository;
    private final StudyroomRepository studyroomRepository;
    private final StudyroomImageRepository studyroomImageRepository;

    public List<Moim> readAll() {
        return moimRepository.findAll();
    }

    public List<MoimDTO> readBest() {
        List<MoimDTO> list = new ArrayList<>();

        moimRepository.findTop8ByOrderByHitsDesc().forEach(moim -> {
            // 타입
            String type = moim.getType().equals("project") ? "프로젝트" : "스터디";

            // 모임 카테고리
            String category = moim.getMoimDetail().getCategory()!=null ? moim.getMoimDetail().getCategory().getCategoryName() : moim.getMoimDetail().getStudyCategory().getStudyCategoryName();

            // 모임 사용언어/기술
            List<LanguageDTO> languageList = new ArrayList<>();
            moimLanguageRepository.findByMoim(moim).forEach(moimLanguage -> {
                languageList.add(
                        LanguageDTO.builder()
                                .name(moimLanguage.getName())
                                .path(LanguageImgPathConvert.getImgPath(moimLanguage.getName()))
                                .build()
                );
            });

            // 모집 직무/인원
            List<HeadcountDTO> headcountList = new ArrayList<>();
            moimHeadcountRepository.findByMoim(moim).forEach(moimHeadcount -> {
                PositionDetail positionDetail = moimHeadcount.getPositionDetail();
                String positionName = positionDetail!=null ? positionDetail.getPosition().getBigName() : "무관";
                String positionDetailName = positionDetail!=null ? positionDetail.getMiddleName() : "누구나 가능";

                headcountList.add(
                        HeadcountDTO.builder()
                                .no(moimHeadcount.getMoimHeadcountNo())
                                .personnel(moimHeadcount.getPersonnel())
                                .positionName(positionName)
                                .positionDetailName(positionDetailName)
                                .approvalNum(moimParticipantsRepository.countByMoimHeadcountAndStatus(moimHeadcount, 2))
                                .build()
                );
            });

            list.add(
                    MoimDTO.builder()
                            .no(moim.getMoimNo())
                            .img(moim.getMoimDetail().getMoimImg())
                            .type(type)
                            .category(category)
                            .subject(moim.getSubject())
                            .desc(moim.getShortDesc())
                            .like(moimLikeRepository.countByMoim(moim))
                            .languageList(languageList)
                            .headcountList(headcountList)
                            .build()
            );
        });

        return list;
    }
}
