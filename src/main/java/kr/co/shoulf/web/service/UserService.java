package kr.co.shoulf.web.service;

import kr.co.shoulf.web.dto.UserDTO;
import kr.co.shoulf.web.entity.UserJob;
import kr.co.shoulf.web.entity.UserOnline;
import kr.co.shoulf.web.repository.MoimParticipantsRepository;
import kr.co.shoulf.web.repository.UserJobRepository;
import kr.co.shoulf.web.repository.UserOnlineRepository;
import kr.co.shoulf.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserJobRepository userJobRepository;
    private final UserOnlineRepository userOnlineRepository;

    public List<UserDTO> readNewUsers() {
        List<UserDTO> userList = new ArrayList<>();

        userRepository.findTop12ByOrderByUserNoDesc().forEach(user -> {
            UserJob userJob = userJobRepository.findByUsers(user);
            UserOnline userOnline = userOnlineRepository.findByUsers(user);

            Integer userPositionLevel = userJob!=null ? userJob.getLevel() : null;
            String userPositionDetailName = userJob!=null ? userJob.getPositionDetail().getMiddleName() : null;
            String userProfileImg = user.getUserDetail().getProfileImg()!=null ? user.getUserDetail().getProfileImg() : "https://letspl.me/assets/images/prof-no-img.png";

            userList.add(
                    UserDTO.builder()
                            .no(user.getUserNo())
                            .nickname(user.getNickname())
                            .profileImg(userProfileImg)
                            .introduce(user.getUserDetail().getIntroduce())
                            .positionLevel(userPositionLevel)
                            .positionDetailName(userPositionDetailName)
                            .build()
            );
        });

        return userList;
    }
}
