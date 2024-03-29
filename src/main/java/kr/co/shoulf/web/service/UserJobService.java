package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.UserJob;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.repository.PositionDetailRepository;
import kr.co.shoulf.web.repository.UserJobRepository;
import kr.co.shoulf.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJobService {
    private final UserJobRepository userJobRepository;
    private final PositionDetailRepository positionDetailRepository;
    private final UserRepository userRepository;
    public UserJob readById(Users users) {
        return userJobRepository.findByUsers(users);
    }
//
//    public List<SearchUserDTO> readByUserJob(Integer positionNo) {
//        List<PositionDetail> positionDetailList = positionDetailRepository.findByPosition_PositionNo(positionNo);
//
//        List<SearchUserDTO> userPositionDTOS = new ArrayList<>();
//
//        for (PositionDetail positionDetail : positionDetailList) {
//            List<UserJob> jobs = userJobRepository.findByPositionDetail(positionDetail);
//
//            for (UserJob userJob : jobs) {
//                Users user = userJob.getUsers();
//
//                SearchUserDTO userPositionDTO = SearchUserDTO.builder()
//                        .userNo(user.getUserNo())
//                        .nickname(user.getNickname())
//                        .userDetail(user.getUserDetail())
//                        .userJob(userJob)
//                        .positionDetail(userJob.getPositionDetail())
//                        .build();
//
//                userPositionDTOS.add(userPositionDTO);
//            }
//        }
//
//        return userPositionDTOS;
//    }
}
