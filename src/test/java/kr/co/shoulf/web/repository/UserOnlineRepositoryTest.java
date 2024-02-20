package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Online;
import kr.co.shoulf.web.entity.UserDetail;
import kr.co.shoulf.web.entity.UserInterestCategory;
import kr.co.shoulf.web.entity.UserOnline;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserOnlineRepositoryTest {
    @Autowired
    private UserOnlineRepository userOnlineRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private OnlineRepository onlineRepository;

    @Test
    @DisplayName("회원 온/오프라인 추가")
    void testInsertUserOnline() {
        ArrayList<UserOnline> test = new ArrayList<>();
        List<UserDetail> userDetails = userDetailRepository.findAll();
        List<Online> onlines = onlineRepository.findAll();

        test.add(UserOnline.builder().userDetail(userDetails.get(0)).online(onlines.get(0)).build());
        test.add(UserOnline.builder().userDetail(userDetails.get(1)).online(onlines.get(0)).build());
        test.add(UserOnline.builder().userDetail(userDetails.get(2)).online(onlines.get(0)).build());
        test.add(UserOnline.builder().userDetail(userDetails.get(3)).online(onlines.get(0)).build());
        test.add(UserOnline.builder().userDetail(userDetails.get(4)).online(onlines.get(0)).build());
        test.add(UserOnline.builder().userDetail(userDetails.get(5)).online(onlines.get(0)).build());
        test.add(UserOnline.builder().userDetail(userDetails.get(6)).online(onlines.get(1)).build());
        test.add(UserOnline.builder().userDetail(userDetails.get(7)).online(onlines.get(2)).build());
        test.add(UserOnline.builder().userDetail(userDetails.get(8)).online(onlines.get(0)).build());
        test.add(UserOnline.builder().userDetail(userDetails.get(9)).online(onlines.get(0)).build());

        test.forEach(userOnline -> userOnlineRepository.save(userOnline));

    }

}