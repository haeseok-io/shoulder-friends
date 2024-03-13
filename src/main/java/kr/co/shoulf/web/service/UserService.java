package kr.co.shoulf.web.service;

import kr.co.shoulf.web.dto.LanguageDTO;
import kr.co.shoulf.web.dto.MoimDTO;
import kr.co.shoulf.web.dto.UserDTO;
import kr.co.shoulf.web.entity.*;
import kr.co.shoulf.web.repository.*;
import kr.co.shoulf.web.util.LanguageImgPathConvert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
