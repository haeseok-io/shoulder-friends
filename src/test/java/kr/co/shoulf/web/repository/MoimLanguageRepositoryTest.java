package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
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
    MoimRepository moimRepository;

    @Test
    void insertData(){
        List<Moim> moimList = moimRepository.findAll();
        List<MoimLanguage> moimLanguageList = new ArrayList<>();
        moimLanguageList.add(MoimLanguage.builder().name("java").moim(moimList.get(0)).build());
        moimLanguageList.add(MoimLanguage.builder().name("spring").moim(moimList.get(0)).build());
        moimLanguageList.add(MoimLanguage.builder().name("javascript").moim(moimList.get(0)).build());
        moimLanguageList.add(MoimLanguage.builder().name("kotlin").moim(moimList.get(1)).build());
        moimLanguageList.add(MoimLanguage.builder().name("php").moim(moimList.get(2)).build());
        moimLanguageList.add(MoimLanguage.builder().name("javascript").moim(moimList.get(2)).build());
        moimLanguageList.add(MoimLanguage.builder().name("html").moim(moimList.get(2)).build());
        moimLanguageList.add(MoimLanguage.builder().name("css").moim(moimList.get(2)).build());
        moimLanguageList.add(MoimLanguage.builder().name("java").moim(moimList.get(3)).build());
        moimLanguageList.add(MoimLanguage.builder().name("spring").moim(moimList.get(3)).build());
        moimLanguageList.add(MoimLanguage.builder().name("swift").moim(moimList.get(4)).build());
        moimLanguageList.add(MoimLanguage.builder().name("java").moim(moimList.get(5)).build());
        moimLanguageList.add(MoimLanguage.builder().name("spring").moim(moimList.get(5)).build());
        moimLanguageList.add(MoimLanguage.builder().name("python").moim(moimList.get(6)).build());
        moimLanguageList.add(MoimLanguage.builder().name("javascript").moim(moimList.get(6)).build());
        moimLanguageList.add(MoimLanguage.builder().name("java").moim(moimList.get(7)).build());
        moimLanguageList.add(MoimLanguage.builder().name("spring").moim(moimList.get(7)).build());
        moimLanguageList.add(MoimLanguage.builder().name("javascript").moim(moimList.get(7)).build());
        moimLanguageList.add(MoimLanguage.builder().name("java").moim(moimList.get(8)).build());
        moimLanguageList.add(MoimLanguage.builder().name("spring").moim(moimList.get(8)).build());
        moimLanguageList.add(MoimLanguage.builder().name("kotlin").moim(moimList.get(9)).build());
        moimLanguageList.forEach(moimLanguage -> moimLanguageRepository.save(moimLanguage));
    }
}