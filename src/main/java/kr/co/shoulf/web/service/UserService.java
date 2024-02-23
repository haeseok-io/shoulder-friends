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
    private final MoimParticipantsRepository moimParticipantsRepository;

    public List<UserDTO> readNewUsers() {
        List<UserDTO> userList = new ArrayList<>();

        userRepository.findTop12ByOrderByUserNoDesc().forEach(user -> {
            UserJob userJob = userJobRepository.findByUsers(user);
            UserOnline userOnline = userOnlineRepository.findByUsers(user);

            userList.add(
                    UserDTO.builder()
                            .userNo(user.getUserNo())
                            .nickname(user.getNickname())
                            .profileImg(user.getUserDetail().getProfileImg())
                            .introduce(user.getUserDetail().getIntroduce())
                            .positionLevel(userJob.getLevel())
                            .positionDetailName(userJob.getPositionDetail().getMiddleName())
                            .online(userOnline.getOnline().getOnlineName())
                            .progressMoimNum(moimParticipantsRepository.countByUsersAndStatus(user, 2))
                            .build()
            );
        });

        return userList;
    }
}
