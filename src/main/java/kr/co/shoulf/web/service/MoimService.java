package kr.co.shoulf.web.service;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
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
import java.util.*;

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

    public PageResponseDTO<MoimDTO> readMoimList(MoimListRequestDTO moimListRequestDTO) {
        Pageable pageable = moimListRequestDTO.getPageRequestDTO().getPageable("moimNo");

        Page<Moim> pageList = moimRepository.selectByTypeAndPositionAndKeyword(moimListRequestDTO.getType(), moimListRequestDTO.getPositionNo(), moimListRequestDTO.getPositionDetailNo(), moimListRequestDTO.getKeyword(), pageable);
        List<MoimDTO> moimList = convertMoimDTOList(pageList.toList());

        PageResponseDTO<MoimDTO> resultList = PageResponseDTO.<MoimDTO>pageBuilder()
                .pageRequestDTO(moimListRequestDTO.getPageRequestDTO())
                .dataList(moimList)
                .total((int) pageList.getTotalElements())
                .build();

        return resultList;
    }

    public List<MoimDTO> readNewList() {
        return convertMoimDTOList(moimRepository.findTop2ByOrderByMoimNoDesc());
    }

    public List<MoimDTO> readBestList() {
        return convertMoimDTOList(moimRepository.findTop8ByOrderByHitsDesc());
    }

    public List<MoimDTO> readSelfWriteList(Long userNo) {
        return convertMoimDTOList(moimRepository.findByUsers_UserNo(userNo));
    }

    public List<MoimDTO> readJoinList(Long userNo) {
        return convertMoimDTOList(moimRepository.selectByMoimParticipants_UserNoAndStatus(userNo, 2));
    }

    public MoimDTO readOne(Long moimNo) {
        return convertMoimDTO(moimRepository.findById(moimNo).orElse(null));
    }

    //모임 지원하기
    public void applyParticipant(Integer positionDetailNo, Long moimHeadcountNo, HttpSession session, MoimParticipants moimParticipants) {
        Users users = (Users) session.getAttribute("loggedInUser");
        Optional<PositionDetail> positionDetailResult = positionDetailRepository.findById(positionDetailNo);
        PositionDetail positionDetail = positionDetailResult.get();
        Optional<MoimHeadcount> moimHeadcountResult = moimHeadcountRepository.findById(moimHeadcountNo);
        MoimHeadcount moimHeadcount = moimHeadcountResult.get();
        moimHeadcount.setPositionDetail(positionDetail);
        MoimParticipants insertData = MoimParticipants.builder()
                        .reason(moimParticipants.getReason())
                        .job(moimParticipants.getJob())
                        .users(users)
                        .moimHeadcount(moimHeadcount)
                        .status(1)
                        .build();
        moimParticipantsRepository.save(insertData);
    }

    // 모임 등록/수정
    public boolean addOne(MoimDataRequestDTO dto, HttpSession session) {
        Users users = (Users) session.getAttribute("loggedInUser");
        Online online = onlineRepository.findById(dto.getOnlineNo()).orElse(null);
        Category category = dto.getCategoryNo()!=null ? categoryRepository.findById(dto.getCategoryNo()).orElse(null) : null;
        StudyCategory studyCategory = dto.getStudyCategoryNo()!=null ? studyCategoryRepository.findById(dto.getStudyCategoryNo()).orElse(null) : null;

        // 이미지 업로드
        String moimImgName = "";
        if( dto.getMoimImg()!=null && !dto.getMoimImg().isEmpty() ) {
            MultipartFile file = dto.getMoimImg();
            String[] fileInfo = file.getOriginalFilename().split("\\.");
            String fileSuffix = fileInfo[fileInfo.length - 1];

            moimImgName = "moim"+UUID.randomUUID().toString()+"."+fileSuffix;

            // 파일 업로드
            Path savePath = Paths.get(uploadPath+"moim/", moimImgName);
            try {
                file.transferTo(savePath);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            // 파일명에 경로 병합
            moimImgName = "/upload/moim/"+moimImgName;
        } else {
            Moim orgMoim = dto.getMoimNo()!=null ? moimRepository.findById(dto.getMoimNo()).orElse(null) : null;
            moimImgName = orgMoim!=null ? orgMoim.getMoimDetail().getMoimImg() : null;
        }


        // 오임 등록/수정
        Moim moim = Moim.builder()
                .moimNo(dto.getMoimNo())
                .type(dto.getType())
                .subject(dto.getSubject())
                .shortDesc(dto.getShortDesc())
                .users(users)
                .build();

        MoimDetail moimDetail = MoimDetail.builder()
                .moimNo(moim.getMoimNo())
                .detailDesc(dto.getDetailDesc())
                .moimImg(moimImgName)
                .fee(dto.getFee())
                .offAddr(dto.getOffAddr())
                .online(online)
                .category(category)
                .studyCategory(studyCategory)
                .moim(moim)
                .build();


        moimDetailRepository.save(moimDetail);
        Moim newMoim = moimRepository.save(moim);

        // 출시 플랫폼 등록
        List<MoimProjectPlatform> moimProjectPlatformList = moimProjectPlatformRepository.findByMoim(newMoim);
        if( !moimProjectPlatformList.isEmpty() ) {
            moimProjectPlatformRepository.deleteAll(moimProjectPlatformList);
        }

        if( dto.getPlatformNo()!=null && !dto.getPlatformNo().isEmpty() ) {
            dto.getPlatformNo().forEach(platformNo -> {
                Platform platform = platformRepository.findById(platformNo).orElse(null);

                moimProjectPlatformRepository.save(
                        MoimProjectPlatform.builder()
                                .platform(platform)
                                .moim(newMoim)
                                .build()
                );
            });
        }

        // 모집인원 등록/수정
        if( dto.getPersonnel()!=null && !dto.getPersonnel().isEmpty() ) {
            // 제거된 모집인원 처리
            List<MoimHeadcount> moimHeadcountList = moimHeadcountRepository.findByMoim(newMoim);
            if( moimHeadcountList!=null && !moimHeadcountList.isEmpty() ) {
                List<MoimHeadcount> deleteMoimHeadcountList = moimHeadcountList.stream().filter(headcount -> {
                    return !dto.getHeadcountNo().contains(headcount.getMoimHeadcountNo());
                }).toList();
                moimHeadcountRepository.deleteAll(deleteMoimHeadcountList);
            }

            // 기존 값 수정
            for(int i=0; i<dto.getPersonnel().size(); i++) {
                Long moimHeadcountNo = dto.getHeadcountNo()==null || dto.getHeadcountNo().isEmpty() ? null : dto.getHeadcountNo().get(i);
                Integer positionDetailNo = dto.getPositionDetailNo()==null || dto.getPositionDetailNo().isEmpty() ? null : dto.getPositionDetailNo().get(i);
                Integer personnel = dto.getPersonnel().get(i);

                PositionDetail positionDetail = positionDetailNo!=null ? positionDetailRepository.findById(positionDetailNo).orElse(null) : null;

                moimHeadcountRepository.save(
                        MoimHeadcount.builder()
                                .moimHeadcountNo(moimHeadcountNo)
                                .positionDetail(positionDetail)
                                .personnel(personnel)
                                .moim(newMoim)
                                .build()
                );
            }
        }


        // 사용언어/기술 등록
        List<MoimLanguage> moimLanguageList = moimLanguageRepository.findByMoim(newMoim);
        if( !moimLanguageList.isEmpty() ) {
            moimLanguageRepository.deleteAll(moimLanguageList);
        }

        if( !dto.getLanguage().isEmpty() ) {
            dto.getLanguage().forEach(language -> {
                moimLanguageRepository.save(
                        MoimLanguage.builder()
                                .name(language)
                                .moim(newMoim)
                                .build()
                );
            });
        }

        // 참고링크
        if( dto.getLink()!=null && !dto.getLink().isEmpty() ) {
            for(int i=0; i<dto.getLink().size(); i++) {
                Long linkNo = dto.getLinkNo().isEmpty() ? null : dto.getLinkNo().get(i);
                String linkUrl = dto.getLink().get(i);

                moimProjectLinkRepository.save(
                        MoimProjectLink.builder()
                                .moimProjectLinkNo(linkNo)
                                .url(linkUrl)
                                .moim(newMoim)
                                .build()
                );
            }
        }

        return true;
    }

    // 모입 수정
    public MoimDataRequestDTO readModifyOne(Long moimNo) {
        Moim moim = moimRepository.findById(moimNo).orElse(null);

        if( moim!=null ) {
            Category category = moim.getMoimDetail().getCategory();
            StudyCategory studyCategory = moim.getMoimDetail().getStudyCategory();

            return MoimDataRequestDTO.builder()
                    .moimNo(moimNo)
                    .type(moim.getType())
                    .subject(moim.getSubject())
                    .shortDesc(moim.getShortDesc())
                    .detailDesc(moim.getMoimDetail().getDetailDesc())
                    .moimImgPath(moim.getMoimDetail().getMoimImg())
                    .categoryNo(category!=null ? category.getCategoryNo() : null)
                    .studyCategoryNo(studyCategory!=null ? studyCategory.getStudyCategoryNo() : null)
                    .platformNo(moimProjectPlatformRepository.findByMoim(moim).stream().map(p -> p.getPlatform().getPlatformNo()).toList())
                    .onlineNo(moim.getMoimDetail().getOnline().getOnlineNo())
                    .offAddr(moim.getMoimDetail().getOffAddr())
                    .fee(moim.getMoimDetail().getFee())
                    .headcountNo(moim.getMoimHeadcountList().stream().map(MoimHeadcount::getMoimHeadcountNo).toList())
                    .positionNo(moim.getMoimHeadcountList().stream().map(h -> {
                        return h.getPositionDetail()!=null ? h.getPositionDetail().getPosition().getPositionNo() : null;
                    }).toList())
                    .positionDetailNo(moim.getMoimHeadcountList().stream().map(h -> {
                        return h.getPositionDetail()!=null ? h.getPositionDetail().getPositionDetailNo() : null;
                    }).toList())
                    .personnel(moim.getMoimHeadcountList().stream().map(MoimHeadcount::getPersonnel).toList())
                    .language(moim.getMoimLanguageList().stream().map(MoimLanguage::getName).toList())
                    .linkNo(moimProjectLinkRepository.findByMoim(moim).stream().map(MoimProjectLink::getMoimProjectLinkNo).toList())
                    .link(moimProjectLinkRepository.findByMoim(moim).stream().map(MoimProjectLink::getUrl).toList())
                    .build();
        } else {
            return null;
        }
    }

    // 모임 데이터 DTO 로 변환
    private MoimDTO convertMoimDTO(Moim moim) {
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

        return MoimDTO.builder()
                .moimNo(moim.getMoimNo())
                .type(moim.getType())
                .subject(moim.getSubject())
                .shortDesc(moim.getShortDesc())
                .status(moim.getStatus())
                .hits(moim.getHits())
                .users(moim.getUsers())
                .moimDetail(moim.getMoimDetail())
                .languageList(languageList)
                .headcountList(headcountList)
                .likeList(moimLikeRepository.findByMoim(moim))
                .projectPlatformList(moimProjectPlatformRepository.findByMoim(moim))
                .build();
    }

    private List<MoimDTO> convertMoimDTOList(List<Moim> moimList) {
        List<MoimDTO> list = new ArrayList<>();

        moimList.forEach(moim -> {
            // 데이터 담기
            list.add(convertMoimDTO(moim));
        });

        return list;
    }

    public void approveParticipant(Long participantNo) {
        MoimParticipants moimParticipants = moimParticipantsRepository.findById(participantNo).orElse(null);
        moimParticipants.setStatus(2);
        moimParticipantsRepository.save(moimParticipants);
    }

    public void rejectParticipant(Long participantNo, String contents) {
        MoimParticipants rejectParticipants = moimParticipantsRepository.findById(participantNo).orElse(null);
        rejectParticipants.setStatus(3);

        MoimParticipantsReject participantsReject  = MoimParticipantsReject.builder()
                .moimParticipants(rejectParticipants)
                .contents(contents)
                .build();
        moimParticipantsRejectRepository.save(participantsReject);
    }

    @Transactional
    public void deleteOne(Long moimNo) {
        Moim deleteMoim = moimRepository.findById(moimNo).orElse(null);
        moimLanguageRepository.findByMoim(deleteMoim).forEach(l->{
            moimLanguageRepository.delete(l);
        });
        moimProjectPlatformRepository.deleteByMoim(deleteMoim);

        moimHeadcountRepository.findByMoim(deleteMoim).forEach(h->{
            h.getMoimParticipantsList().forEach(p->{
                moimParticipantsRejectRepository.deleteByMoimParticipants(p);
            });
            moimParticipantsRepository.deleteByMoimHeadcount(h);
            moimHeadcountRepository.delete(h);
        });
        moimDetailRepository.deleteByMoim(deleteMoim);
        moimRepository.delete(deleteMoim);
    }

    public void complete(Long moimNo) {
        Moim moim = moimRepository.findById(moimNo).orElse(null);
        moim.setStatus(2);
        moimRepository.save(moim);
    }

    public void moimLike(Long moimNo, Users user) {
        MoimLike moimLike = MoimLike.builder()
                .users(user)
                .moim(moimRepository.findById(moimNo).orElse(null))
                .build();
        moimLikeRepository.save(moimLike);
    }

    public MoimLike readLike(Long moimNo, Users user) {
        if(user != null){
            Moim moim = moimRepository.findById(moimNo).orElse(null);
            return moimLikeRepository.findByMoimAndUsers_UserNo(moim, user.getUserNo());
        }
        return null;
    }

    @Transactional
    public void deleteLike(Long moimNo, Users user) {
        Moim moim = moimRepository.findById(moimNo).orElse(null);
        moimLikeRepository.deleteByMoimAndUsers_UserNo(moim, user.getUserNo());
    }
}
