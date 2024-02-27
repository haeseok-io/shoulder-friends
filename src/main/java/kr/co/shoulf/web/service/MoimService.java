package kr.co.shoulf.web.service;

import kr.co.shoulf.web.dto.*;
import kr.co.shoulf.web.entity.*;
import kr.co.shoulf.web.repository.*;
import kr.co.shoulf.web.util.LanguageImgPathConvert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoimService {
    private final MoimRepository moimRepository;
    private final MoimLikeRepository moimLikeRepository;
    private final MoimLanguageRepository moimLanguageRepository;
    private final MoimHeadcountRepository moimHeadcountRepository;
    private final MoimParticipantsRepository moimParticipantsRepository;
    private final MoimParticipantsRejectRepository moimParticipantsRejectRepository;

    public List<Moim> readAll() {
        return null;
    }

    public PageResponseDTO<MoimDTO> readMoimList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("moimNo");

        Page<Moim> pageList = moimRepository.findAll(pageable);
        List<MoimDTO> moimList = getMoimData(pageList.toList());

        PageResponseDTO<MoimDTO> resultList = PageResponseDTO.<MoimDTO>pageBuilder()
                .pageRequestDTO(pageRequestDTO)
                .dataList(moimList)
                .total((int) pageList.getTotalElements())
                .build();

        return resultList;
    }

    public List<MoimDTO> readNewList() {
        return getMoimData(moimRepository.findTop2ByOrderByMoimNoDesc());
    }

    public List<MoimDTO> readBestList() {
        return getMoimData(moimRepository.findTop8ByOrderByHitsDesc());
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
                                .positionDetail(headcount.getPositionDetail())
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
