package kr.co.shoulf.web.service;

import kr.co.shoulf.web.dto.LanguageDTO;
import kr.co.shoulf.web.dto.MoimDTO;
import kr.co.shoulf.web.dto.MoimHeadcountDTO;
import kr.co.shoulf.web.dto.MoimParticipantDTO;
import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.MoimParticipants;
import kr.co.shoulf.web.entity.MoimParticipantsReject;
import kr.co.shoulf.web.entity.PositionDetail;
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
    private final MoimParticipantsRejectRepository moimParticipantsRejectRepository;

    public List<Moim> readAll() {
        return moimRepository.findAll();
    }

    public List<Moim> readNewMoim() { // 신규 모임 가져오기
        return moimRepository.findTop2ByOrderByMoimNoDesc();
    }

    public List<MoimDTO> readBestList() {
        List<MoimDTO> list = getMoimData(moimRepository.findTop8ByOrderByHitsDesc());
        return list;
    }

    private List<MoimDTO> getMoimData(List<Moim> moimList) {
        List<MoimDTO> list = new ArrayList<>();

        moimList.forEach(moim -> {
            // 모임 사용언어/기술
            List<LanguageDTO> languageList = new ArrayList<>();
            moimLanguageRepository.findByMoim(moim).forEach(moimLanguage -> {
                languageList.add(
                        LanguageDTO.builder()
                                .no(moimLanguage.getMoimLanguageNo())
                                .name(moimLanguage.getName())
                                .path(LanguageImgPathConvert.getImgPath(moimLanguage.getName()))
                                .build()
                );
            });

            // 모집 직무/인원
            List<MoimHeadcountDTO> headcountList = new ArrayList<>();
            moimHeadcountRepository.findByMoim(moim).forEach(headcount -> {
                // 지원자
                List<MoimParticipantDTO> participantList = new ArrayList<>();
                List<MoimParticipantDTO> participantApprovalList = new ArrayList<>();
                List<MoimParticipantDTO> participantRejectedList = new ArrayList<>();

                moimParticipantsRepository.findByMoimHeadcount(headcount).forEach(participant -> {
                    MoimParticipantDTO participantDTO = new MoimParticipantDTO();

                    participantDTO.setMoimParticipantsNo(participant.getMoimParticipantsNo());
                    participantDTO.setReason(participant.getReason());
                    participantDTO.setJob(participant.getJob());
                    participantDTO.setStatus(participant.getStatus());
                    participantDTO.setUsers(participant.getUsers());

                    if( participant.getStatus().equals(3) ) {
                        MoimParticipantsReject moimParticipantsReject = moimParticipantsRejectRepository.findByMoimParticipants_MoimParticipantsNo(participant.getMoimParticipantsNo());
                        participantDTO.setReject(moimParticipantsReject);
                    }

                    participantList.add(participantDTO);
                    if( participant.getStatus().equals(2) )         participantApprovalList.add(participantDTO);
                    else if( participant.getStatus().equals(3) )    participantRejectedList.add(participantDTO);
                });


                headcountList.add(
                        MoimHeadcountDTO.builder()
                                .moimHeadcountNo(headcount.getMoimHeadcountNo())
                                .personnel(headcount.getPersonnel())
                                .participantList(participantList)
                                .participantApprovalList(participantApprovalList)
                                .participantRejectedList(participantRejectedList)
                                .build()
                );
            });

            // 데이터 담기
            list.add(
                    MoimDTO.builder()
                            .moimNo(moim.getMoimNo())
                            .type(moim.getType())
                            .subject(moim.getSubject())
                            .shortDesc(moim.getShortDesc())
                            .status(moim.getStatus())
                            .hits(moim.getHits())
                            .moimDetail(moim.getMoimDetail())
                            .languageList(languageList)
                            .headcountList(headcountList)
                            .likeList(moimLikeRepository.findByMoim(moim))
                            .build()
            );
        });

        return list;
    }
}
