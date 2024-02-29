package kr.co.shoulf.web.service;

import kr.co.shoulf.web.dto.UserDTO;
import kr.co.shoulf.web.dto.UserDetailDTO;
import kr.co.shoulf.web.entity.UserJob;
import kr.co.shoulf.web.entity.UserOnline;
import kr.co.shoulf.web.entity.UserPortfolio;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.repository.*;
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
    private final UserRepository userRepository;
    private final UserJobRepository userJobRepository;
    private final UserOnlineRepository userOnlineRepository;
    private final UserPortfolioRepository userPortfolioRepository;
    private final UserLanguageRepository userLanguageRepository;
    private final UserInterestCategoryRepository userInterestCategoryRepository;

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

    public UserDetailDTO readUserDetail(Long userNo) {
        Optional<Users> usersOptional = userRepository.findById(userNo);

        // 조회되는 사용자가 없을 경우
        if( usersOptional.isEmpty() )    return null;

        Users users = usersOptional.get();



        return UserDetailDTO.builder().build();
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
