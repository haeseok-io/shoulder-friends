package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserJobRepositoryTest {
    @Autowired
    private UserJobRepository userJobRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PositionDetailRepository positionDetailRepository;

    @Test
    @DisplayName("회원 직무 정보 추가")
    void testInsertUserJob() {
        ArrayList<UserJob> test = new ArrayList<>();
        List<Users> users = userRepository.findAll();
        List<PositionDetail> positionDetails = positionDetailRepository.findAll();

        test.add(UserJob.builder().users(users.get(0)).level(5).career(10).positionDetail(positionDetails.get(2)).build());
        test.add(UserJob.builder().users(users.get(1)).level(4).career(7).positionDetail(positionDetails.get(24)).build());
        test.add(UserJob.builder().users(users.get(2)).level(1).career(0).positionDetail(positionDetails.get(0)).build());
        test.add(UserJob.builder().users(users.get(3)).level(3).career(5).positionDetail(positionDetails.get(14)).build());
        test.add(UserJob.builder().users(users.get(4)).level(3).career(3).positionDetail(positionDetails.get(7)).build());
        test.add(UserJob.builder().users(users.get(5)).level(1).career(1).positionDetail(positionDetails.get(22)).build());
        test.add(UserJob.builder().users(users.get(6)).level(5).career(8).positionDetail(positionDetails.get(8)).build());
        test.add(UserJob.builder().users(users.get(7)).level(4).career(5).positionDetail(positionDetails.get(22)).build());
        test.add(UserJob.builder().users(users.get(8)).level(1).career(0).positionDetail(positionDetails.get(16)).build());
        test.add(UserJob.builder().users(users.get(9)).level(3).career(3).positionDetail(positionDetails.get(22)).build());

        test.forEach(userJob -> userJobRepository.save(userJob));

    }

}