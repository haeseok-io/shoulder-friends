package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MoimDetail;
import kr.co.shoulf.web.entity.MoimProjectLink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MoimProjectLinkRepositoryTest {
    @Autowired
    MoimProjectLinkRepository moimProjectLinkRepository;
    @Autowired
    MoimDetailRepository moimDetailRepository;

    @Test
    void insertData(){
        List<MoimDetail> moimDetailList = moimDetailRepository.findAll();
        List<MoimProjectLink> list = new ArrayList<>();
        list.add(MoimProjectLink.builder().url("https://github.com/bbb").moimDetail(moimDetailList.get(1)).build());
        list.add(MoimProjectLink.builder().url("https://github.com/fff").moimDetail(moimDetailList.get(5)).build());
        list.add(MoimProjectLink.builder().url("https://github.com/iii").moimDetail(moimDetailList.get(8)).build());
        list.forEach(moimProjectLink -> moimProjectLinkRepository.save(moimProjectLink));
    }

}