package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.MoimDetail;
import kr.co.shoulf.web.entity.MoimProjectPlatform;
import kr.co.shoulf.web.entity.Platform;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MoimProjectPlatformRepositoryTest {
    @Autowired
    MoimProjectPlatformRepository moimProjectPlatformRepository;
    @Autowired
    PlatformRepository platformRepository;
    @Autowired
    MoimRepository moimRepository;

    @Test
    void insertData(){
        List<Platform> platformList = platformRepository.findAll();
        List<Moim> moimList = moimRepository.findAll();
        List<MoimProjectPlatform> list = new ArrayList<>();
        list.add(MoimProjectPlatform.builder().platform(platformList.get(0)).moim(moimList.get(0)).build());
        list.add(MoimProjectPlatform.builder().platform(platformList.get(0)).moim(moimList.get(1)).build());
        list.add(MoimProjectPlatform.builder().platform(platformList.get(1)).moim(moimList.get(4)).build());
        list.add(MoimProjectPlatform.builder().platform(platformList.get(0)).moim(moimList.get(5)).build());
        list.add(MoimProjectPlatform.builder().platform(platformList.get(2)).moim(moimList.get(8)).build());
        list.add(MoimProjectPlatform.builder().platform(platformList.get(3)).moim(moimList.get(9)).build());
        list.forEach(moimProjectPlatform -> moimProjectPlatformRepository.save(moimProjectPlatform));
    }

}