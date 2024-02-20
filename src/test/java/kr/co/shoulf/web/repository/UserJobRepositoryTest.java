package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.PositionDetail;
import kr.co.shoulf.web.entity.UserDetail;
import kr.co.shoulf.web.entity.UserInterestCategory;
import kr.co.shoulf.web.entity.UserJob;
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
    private UserDetailRepository userDetailRepository;
    @Autowired
    private PositionDetailRepository positionDetailRepository;

    @Test
    @DisplayName("회원 직무 정보 추가")
    void testInsertUserJob() {
        ArrayList<UserJob> test = new ArrayList<>();
        List<UserDetail> userDetails = userDetailRepository.findAll();
        List<PositionDetail> positionDetails = positionDetailRepository.findAll();

        test.add(UserJob.builder().userDetail(userDetails.get(0)).level(5).career(10).positionDetail(positionDetails.get(2)).build());
        test.add(UserJob.builder().userDetail(userDetails.get(1)).level(4).career(7).positionDetail(positionDetails.get(24)).build());
        test.add(UserJob.builder().userDetail(userDetails.get(2)).level(1).career(0).positionDetail(positionDetails.get(0)).build());
        test.add(UserJob.builder().userDetail(userDetails.get(3)).level(3).career(5).positionDetail(positionDetails.get(14)).build());
        test.add(UserJob.builder().userDetail(userDetails.get(4)).level(3).career(3).positionDetail(positionDetails.get(7)).build());
        test.add(UserJob.builder().userDetail(userDetails.get(5)).level(1).career(1).positionDetail(positionDetails.get(22)).build());
        test.add(UserJob.builder().userDetail(userDetails.get(6)).level(5).career(8).positionDetail(positionDetails.get(8)).build());
        test.add(UserJob.builder().userDetail(userDetails.get(7)).level(4).career(5).positionDetail(positionDetails.get(22)).build());
        test.add(UserJob.builder().userDetail(userDetails.get(8)).level(1).career(0).positionDetail(positionDetails.get(16)).build());
        test.add(UserJob.builder().userDetail(userDetails.get(9)).level(3).career(3).positionDetail(positionDetails.get(22)).build());

        test.forEach(userJob -> userJobRepository.save(userJob));

    }

}