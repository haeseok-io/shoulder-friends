package kr.co.shoulf.web.service;

import kr.co.shoulf.web.dto.*;
import kr.co.shoulf.web.entity.*;
import kr.co.shoulf.web.repository.*;
import kr.co.shoulf.web.util.LanguageImgPathConvert;
import kr.co.shoulf.web.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final MoimService moimService;
    private final UserRepository userRepository;
    private final UserJobRepository userJobRepository;
    private final UserOnlineRepository userOnlineRepository;
    private final UserPortfolioRepository userPortfolioRepository;
    private final UserLanguageRepository userLanguageRepository;
    private final UserInterestCategoryRepository userInterestCategoryRepository;
    private final MoimLikeRepository moimLikeRepository;
    private final MoimRepository moimRepository;
    private final MoimParticipantsRepository moimParticipantsRepository;
    private final PositionDetailRepository positionDetailRepository;
    private final OnlineRepository onlineRepository;
    private final CategoryRepository categoryRepository;

    @Value("${kr.co.shoulf.upload.directory}")
    private String uploadPath;

    public List<UserDTO> readSearchUser(Integer positionNo, Integer positionDetailNo, String keyword) {
        return userRepository.selectByTypeAndPositionAndKeyword(positionNo, positionDetailNo, keyword)
                .stream().map(user -> {
                    UserDTO userDTO = UserDTO.builder()
                            .userNo(user.getUserNo())
                            .nickname(user.getNickname())
                            .userOnline(userOnlineRepository.findByUsers(user))
                            .userJob(userJobRepository.findByUsers(user))
                            .build();
                    return userDTO;
                })
                .collect(Collectors.toList());
    }

    // 신규 회원 리스트
    public List<UserDTO> readNewUserList() {
        List<UserDTO> userList = new ArrayList<>();

        userRepository.findTop12ByOrderByUserNoDesc().forEach(user -> {
            userList.add(
                    UserDTO.builder()
                            .userNo(user.getUserNo())
                            .nickname(user.getNickname())
                            .userDetail(user.getUserDetail())
                            .userJob(userJobRepository.findByUsers(user))
                            .build()
            );
        });

        return userList;
    }

    public List<FindUserDTO> readFindUserList() {
        return userRepository.findAll().stream()
                .map(user -> {
                    return FindUserDTO.builder()
                            .userNo(user.getUserNo())
                            .nickname(user.getNickname())
                            .userDetail(user.getUserDetail())
                            .userJob(userJobRepository.findByUsers(user))
                            .moim(moimRepository.findByUsers(user))
                            .build();
                })
                .collect(Collectors.toList());
    }

    public FindUserDTO readRandomFindUser() {
        List<FindUserDTO> findUserList = readFindUserList();
        return RandomUtil.getRandomElement(findUserList);
    }

    // 회원 상세
    public UserDTO readUserDetail(Long userNo) {
        Optional<Users> usersOptional = userRepository.findById(userNo);

        // 조회되는 사용자가 없을 경우
        if( usersOptional.isEmpty() )    return null;

        Users users = usersOptional.get();
        UserJob userJob = userJobRepository.findByUsers(users);
        UserOnline userOnline = userOnlineRepository.findByUsers(users);
        List<UserPortfolio> userPortfolioList = userPortfolioRepository.findByUsers(users);
        List<LanguageDTO> userLanguageList = userLanguageRepository.findByUsers(users).stream().map(l -> LanguageDTO.builder().no(l.getUserLanguageNo()).name(l.getName()).path(LanguageImgPathConvert.getImgPath(l.getName())).build()).toList();
        List<UserInterestCategory> userInterestCategoryList = userInterestCategoryRepository.findByUsers(users);
        List<MoimLike> moimLikeList = moimLikeRepository.findByUsers(users);
        List<MoimDTO> progressMoimList = moimParticipantsRepository.findByUsersAndStatus(users, 2).stream().map(x -> {
            Moim m = moimRepository.findByMoimHeadcountList_MoimHeadcountNo(x.getMoimHeadcount().getMoimHeadcountNo());
            return moimService.readOne(m.getMoimNo());
            //return ;
        }).toList();

        return UserDTO.builder()
                .userNo(users.getUserNo())
                .email(users.getEmail())
                .nickname(users.getNickname())
                .userDetail(users.getUserDetail())
                .userJob(userJob)
                .userOnline(userOnline)
                .portfolioList(userPortfolioList)
                .languageList(userLanguageList)
                .interestCategoryList(userInterestCategoryList)
                .moimLikeList(moimLikeList)
                .progressMoim(progressMoimList)
                .build();
    }

    public UserDTO readOne(long userNo) {
        Optional<Users> result = userRepository.findById(userNo);
        Users users = result.get();
        UserDTO dto = UserDTO.builder()
                .userNo(users.getUserNo())
                .email(users.getEmail())
                .nickname(users.getNickname())
                .pass(users.getPass())
                .build();
        return dto;
    }

    // 회원 정보 수정
    public Map<Boolean, String> modifyOne(UserDataRequestDTO userDataRequestDTO) {
        HashMap<Boolean, String> result = new HashMap<>();

        Users users = userRepository.findById(userDataRequestDTO.getUserNo()).orElse(null);
        if( users==null ) {
            result.put(false, "회원 정보가 존재하지 않습니다.");
            return result;
        }

        // 프로필 이미지
        String profileImgName = null;
        if( !userDataRequestDTO.getProfileImg().isEmpty() ) {
            // 기존 이미지가 존재한다면 기존 이미지 삭제
            String beforeImgPath = users.getUserDetail().getProfileImg();
            if( beforeImgPath!=null ) {
                File beforeImg = new File(beforeImgPath);
                if( beforeImg.exists() ) beforeImg.delete();
            }

            // 이미지 업로드
            MultipartFile file = userDataRequestDTO.getProfileImg();
            String[] fileInfo = file.getOriginalFilename().split("\\.");
            String fileSuffix = fileInfo[fileInfo.length - 1];

            profileImgName = "user_"+UUID.randomUUID().toString()+"."+fileSuffix;

            Path savePath = Paths.get(uploadPath+"user/", profileImgName);
            try {
                file.transferTo(savePath);
            } catch(IOException e) {
                result.put(false, "파일 업로드에 실패하였습니다.");
                return result;
            }

            // 파일명에 경로 병합
            profileImgName = "/upload/user/"+profileImgName;
        } else {
            profileImgName = users.getUserDetail().getProfileImg();
        }

        users.setNickname(userDataRequestDTO.getNickname());
        users.getUserDetail().setIntroduce(userDataRequestDTO.getIntroduce());
        users.getUserDetail().setPreferArea(userDataRequestDTO.getPreferArea());
        users.getUserDetail().setGitLink(userDataRequestDTO.getGitLink());
        users.getUserDetail().setBlogLink(userDataRequestDTO.getBlogLink());
        users.getUserDetail().setProfileImg(profileImgName);
        userRepository.save(users);

        // 회원 직무
        PositionDetail positionDetail = null;
        if( userDataRequestDTO.getPositionDetailNo()!=null ) {
            positionDetail = positionDetailRepository.findById(userDataRequestDTO.getPositionDetailNo()).orElse(null);
        }

        UserJob userJob = UserJob.builder()
                .userJobNo(userDataRequestDTO.getUserJobNo())
                .level(userDataRequestDTO.getLevel())
                .career(userDataRequestDTO.getCareer())
                .users(users)
                .positionDetail(positionDetail)
                .build();
        userJobRepository.save(userJob);

        // 회원 온/오프라인
        if( userDataRequestDTO.getOnlineNo()!=null ) {
            Online online = onlineRepository.findById(userDataRequestDTO.getOnlineNo()).orElse(null);
            UserOnline userOnline = UserOnline.builder()
                    .userOnlineNo(userDataRequestDTO.getUserOnlineNo())
                    .users(users)
                    .online(online)
                    .build();

            userOnlineRepository.save(userOnline);
        }

        // 관심분야
        List<UserInterestCategory> userInterestCategoryList = userInterestCategoryRepository.findByUsers(users);
        userInterestCategoryRepository.deleteAll(userInterestCategoryList);
        if( userDataRequestDTO.getCategoryNo()!=null ) {
            userDataRequestDTO.getCategoryNo().forEach(cate -> {
                Category category = categoryRepository.findById(cate).orElse(null);
                userInterestCategoryRepository.save(UserInterestCategory.builder()
                        .users(users)
                        .category(category)
                        .build());
            });
        }

        // 포트폴리오
        // 제거된 포트폴리오 제거
        List<UserPortfolio> deleteUserPortfolioList = userPortfolioRepository.findByUsers(users).stream().filter(portfolio -> {
            return !userDataRequestDTO.getPortfolioNo().contains(portfolio.getPortfolioNo());
        }).toList();
        userPortfolioRepository.deleteAll(deleteUserPortfolioList);

        // 포트폴리오 수정 및 추가
        if( !userDataRequestDTO.getPortfolio().isEmpty() ) {
            for(int i=0; i<userDataRequestDTO.getPortfolio().size(); i++ ) {
                String portfolioUrl = userDataRequestDTO.getPortfolio().get(i);
                Long portfolioNo = userDataRequestDTO.getPortfolioNo().isEmpty() ? null : userDataRequestDTO.getPortfolioNo().get(i);

                userPortfolioRepository.save(UserPortfolio.builder()
                        .portfolioNo(portfolioNo)
                        .url(portfolioUrl)
                        .users(users)
                        .build());
            }
        }


        return result;
    }
}
