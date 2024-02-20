package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Platform;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlatformRepositoryTest {
    @Autowired
    PlatformRepository platformRepository;

    @Test
    void insertData(){
        List<Platform> list = new ArrayList<>();
        list.add(Platform.builder().name("미정(논의 후 확정)").build());
        list.add(Platform.builder().name("반응형 웹(PC/모바일)").build());
        list.add(Platform.builder().name("안드로이드 앱").build());
        list.add(Platform.builder().name("IOS 앱").build());
        list.add(Platform.builder().name("기타").build());
        list.forEach(p->platformRepository.save(p));
    }
}