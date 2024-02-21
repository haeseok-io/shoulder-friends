package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
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
    MoimRepository moimRepository;

    @Test
    void insertData() {
        List<Moim> moimList = moimRepository.findAll();
        List<MoimProjectLink> list = new ArrayList<>();
        list.add(MoimProjectLink.builder().url("https://github.com/bbb").moim(moimList.get(1)).build());
        list.add(MoimProjectLink.builder().url("https://github.com/fff").moim(moimList.get(5)).build());
        list.add(MoimProjectLink.builder().url("https://github.com/iii").moim(moimList.get(8)).build());
        list.forEach(moimProjectLink -> moimProjectLinkRepository.save(moimProjectLink));
    }

}