package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Category;
import kr.co.shoulf.web.entity.Online;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OnlineRepositoryTest {
    @Autowired
    private OnlineRepository onlineRepository;

    @Test
    @DisplayName("온/오프라인 추가")
    void testInsertOnline() {
        ArrayList<Online> test = new ArrayList<>();

        test.add(Online.builder().onlineName("온라인/오프라인 모두 가능").build());
        test.add(Online.builder().onlineName("온라인만 가능").build());
        test.add(Online.builder().onlineName("오프라인만 가능").build());

        test.forEach(online -> onlineRepository.save(online));
    }

}