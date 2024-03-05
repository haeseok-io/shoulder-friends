package kr.co.shoulf.web.service;

import kr.co.shoulf.web.dto.*;
import kr.co.shoulf.web.entity.*;
import kr.co.shoulf.web.repository.*;
import kr.co.shoulf.web.util.LanguageImgPathConvert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoimService {
    private final UserRepository userRepository;
    private final MoimRepository moimRepository;
    private final MoimDetailRepository moimDetailRepository;
    private final MoimLikeRepository moimLikeRepository;
    private final PlatformRepository platformRepository;
    private final MoimProjectPlatformRepository moimProjectPlatformRepository;
    private final MoimHeadcountRepository moimHeadcountRepository;
    private final MoimLanguageRepository moimLanguageRepository;
    private final MoimProjectLinkRepository moimProjectLinkRepository;
    private final MoimParticipantsRepository moimParticipantsRepository;
    private final MoimParticipantsRejectRepository moimParticipantsRejectRepository;
    private final OnlineRepository onlineRepository;
    private final CategoryRepository categoryRepository;
    private final StudyCategoryRepository studyCategoryRepository;
    private final PositionDetailRepository positionDetailRepository;

    @Value("${kr.co.shoulf.upload.directory}")
    private String uploadPath;

    public List<Moim> readAll() {
        return null;
    }

    public PageResponseDTO<MoimDTO> readMoimList(PageRequestDTO pageRequestDTO) {
        pageRequestDTO.setScale(12);
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

    // 모임 등록
    public boolean addOne(MoimDataRequestDTO dto) {
        String fileName = null;

        // 임시 유저 - 추 후 로그인중인 유저로 변경해야함
        Users user = userRepository.findById(1L).orElse(null);
        Online online = onlineRepository.findById(dto.getOnlineNo()).orElse(null);
        Category category = dto.getCategoryNo()==null ? null : categoryRepository.findById(dto.getCategoryNo()).orElse(null);
        StudyCategory studyCategory = dto.getStudyCategoryNo()==null ? null : studyCategoryRepository.findById(dto.getStudyCategoryNo()).orElse(null);

        // 이미지 업로드
        if( dto.getMoimImg()!=null && !dto.getMoimImg().isEmpty() ) {
            MultipartFile file = dto.getMoimImg();
            String[] fileInfo = file.getOriginalFilename().split("\\.");
            String fileSuffix = fileInfo[fileInfo.length - 1];
            fileName = "moim_" + UUID.randomUUID().toString()+"."+fileSuffix;

            Path savePath = Paths.get(uploadPath+"moim/", fileName);
            try {
                file.transferTo(savePath);
            }
            catch (IOException e) {
                return false;
            }
        }

        // 모임 등록
        Moim moim = Moim.builder()
                .type(dto.getType())
                .subject(dto.getSubject())
                .shortDesc(dto.getShortDesc())
                .users(user)
                .build();

        MoimDetail moimDetail = MoimDetail.builder()
                .detailDesc(dto.getDetailDesc())
                .moimImg(fileName)
                .fee(dto.getFee())
                .offAddr(dto.getOffAddr())
                .online(online)
                .category(category)
                .studyCategory(studyCategory)
                .moim(moim)
                .build();

        MoimDetail insertData = moimDetailRepository.save(moimDetail);

        // 출시 플랫폼 등록
        if( dto.getPlatformNo()!=null && !dto.getPlatformNo().isEmpty() ) {
            dto.getPlatformNo().forEach(platformNo -> {
                Platform platform = platformRepository.findById(platformNo).orElse(null);

                moimProjectPlatformRepository.save(
                        MoimProjectPlatform.builder()
                                .platform(platform)
                                .moim(insertData.getMoim())
                                .build()
                );
            });
        }

        // 모집인원 등록
        if( dto.getPositionNo()!=null && !dto.getPositionNo().isEmpty() ) {
            for(int i=0; i<dto.getPositionNo().size(); i++) {
                PositionDetail positionDetail = positionDetailRepository.findById(dto.getPositionDetailNo().get(i)).orElse(null);

                moimHeadcountRepository.save(
                        MoimHeadcount.builder()
                                .personnel(dto.getPersonnel().get(i))
                                .moim(insertData.getMoim())
                                .positionDetail(positionDetail)
                                .build()
                );
            }
        } else {
            moimHeadcountRepository.save(
                    MoimHeadcount.builder()
                            .personnel(dto.getPersonnel().get(0))
                            .moim(insertData.getMoim())
                            .build()
            );
        }

        // 사용언어/기술 등록
        if( !dto.getLanguage().isEmpty() ) {
            dto.getLanguage().forEach(language -> {
                moimLanguageRepository.save(
                        MoimLanguage.builder()
                                .moim(insertData.getMoim())
                                .name(language)
                                .build()
                );
            });
        }

        // 참고자료
        if( dto.getLink()!=null && !dto.getLink().isEmpty() ) {
            dto.getLink().forEach(link -> {
                moimProjectLinkRepository.save(
                        MoimProjectLink.builder()
                                .moim(insertData.getMoim())
                                .url(link)
                                .build()
                );
            });
        }

        return true;
    }

    // 모임 데이터 DTO 로 변환
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
