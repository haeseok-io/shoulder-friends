package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.MoimDetail;
import kr.co.shoulf.web.entity.MoimLanguage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MoimLanguageRepositoryTest {
    @Autowired
    MoimLanguageRepository  moimLanguageRepository;
    @Autowired
    MoimDetailRepository moimDetailRepository;

    @Test
    void insertData(){
        List<MoimDetail> moimDetailList = moimDetailRepository.findAll();
        List<MoimLanguage> moimLanguageList = new ArrayList<>();
        moimLanguageList.add(MoimLanguage.builder().name("java").moimDetail(moimDetailList.get(0)).build());
        moimLanguageList.add(MoimLanguage.builder().name("spring").moimDetail(moimDetailList.get(0)).build());
        moimLanguageList.add(MoimLanguage.builder().name("javascript").moimDetail(moimDetailList.get(0)).build());
        moimLanguageList.add(MoimLanguage.builder().name("kotlin").moimDetail(moimDetailList.get(1)).build());
        moimLanguageList.add(MoimLanguage.builder().name("php").moimDetail(moimDetailList.get(2)).build());
        moimLanguageList.add(MoimLanguage.builder().name("javascript").moimDetail(moimDetailList.get(2)).build());
        moimLanguageList.add(MoimLanguage.builder().name("html").moimDetail(moimDetailList.get(2)).build());
        moimLanguageList.add(MoimLanguage.builder().name("css").moimDetail(moimDetailList.get(2)).build());
        moimLanguageList.add(MoimLanguage.builder().name("java").moimDetail(moimDetailList.get(3)).build());
        moimLanguageList.add(MoimLanguage.builder().name("spring").moimDetail(moimDetailList.get(3)).build());
        moimLanguageList.add(MoimLanguage.builder().name("swift").moimDetail(moimDetailList.get(4)).build());
        moimLanguageList.add(MoimLanguage.builder().name("java").moimDetail(moimDetailList.get(5)).build());
        moimLanguageList.add(MoimLanguage.builder().name("spring").moimDetail(moimDetailList.get(5)).build());
        moimLanguageList.add(MoimLanguage.builder().name("python").moimDetail(moimDetailList.get(6)).build());
        moimLanguageList.add(MoimLanguage.builder().name("javascript").moimDetail(moimDetailList.get(6)).build());
        moimLanguageList.add(MoimLanguage.builder().name("java").moimDetail(moimDetailList.get(7)).build());
        moimLanguageList.add(MoimLanguage.builder().name("spring").moimDetail(moimDetailList.get(7)).build());
        moimLanguageList.add(MoimLanguage.builder().name("javascript").moimDetail(moimDetailList.get(7)).build());
        moimLanguageList.add(MoimLanguage.builder().name("java").moimDetail(moimDetailList.get(8)).build());
        moimLanguageList.add(MoimLanguage.builder().name("spring").moimDetail(moimDetailList.get(8)).build());
        moimLanguageList.add(MoimLanguage.builder().name("kotlin").moimDetail(moimDetailList.get(9)).build());
        moimLanguageList.forEach(moimLanguage -> moimLanguageRepository.save(moimLanguage));
    }
}