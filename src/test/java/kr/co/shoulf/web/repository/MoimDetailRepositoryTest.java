package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MoimDetailRepositoryTest {
    @Autowired
    MoimDetailRepository moimDetailRepository;
    @Autowired
    MoimRepository moimRepository;
    @Autowired
    OnlineRepository onlineRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StudyCategoryRepository studyCategoryRepository;
    @Autowired
    UserRepository userRepository;
    List<Users> usersList;
    List<Online> onlineList;
    List<Category> categoryList;
    List<StudyCategory> studyCategoryList;

    @Test
    void insertData() {
        //moimList = moimRepository.findAll();
        usersList = userRepository.findAll();
        onlineList = onlineRepository.findAll();
        categoryList = categoryRepository.findAll();
        studyCategoryList = studyCategoryRepository.findAll();
        Moim moim1 = Moim.builder().type("proejct").subject("프로젝트1").shortDesc("애견앱 개발").status(1).hits(1).users(usersList.get(0)).build();
        Moim moim2 = Moim.builder().type("proejct").subject("프로젝트2").shortDesc("데이팅앱 개발").status(1).hits(1).users(usersList.get(1)).build();
        Moim moim3 = Moim.builder().type("study").subject("스터디1").shortDesc("스프링스터디").status(1).hits(1).users(usersList.get(2)).build();
        Moim moim4 = Moim.builder().type("study").subject("스터디2").shortDesc("파이썬스터디").status(1).hits(1).users(usersList.get(3)).build();
        Moim moim5 = Moim.builder().type("proejct").subject("프로젝트3").shortDesc("쇼핑몰 개발").status(1).hits(1).users(usersList.get(4)).build();
        Moim moim6 = Moim.builder().type("proejct").subject("프로젝트4").shortDesc("헬스앱 개발").status(1).hits(1).users(usersList.get(5)).build();
        Moim moim7 = Moim.builder().type("study").subject("스터디3").shortDesc("코테 스터디").status(1).hits(1).users(usersList.get(6)).build();
        Moim moim8 = Moim.builder().type("study").subject("스터디4").shortDesc("SQL 스터디").status(1).hits(1).users(usersList.get(7)).build();
        Moim moim9 = Moim.builder().type("proejct").subject("프로젝트5").shortDesc("인공지능 활용앱 개발").status(1).hits(1).users(usersList.get(8)).build();
        Moim moim10 = Moim.builder().type("proejct").subject("프로젝트6").shortDesc("소셜앱 개발").status(1).hits(1).users(usersList.get(9)).build();

        List<MoimDetail> list = new ArrayList<>();
        list.add(MoimDetail.builder().moim(moim1).detailDesc("애견~").moimImg("https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png").fee(20000).offAddr("서울").online(onlineList.get(0)).category(categoryList.get(10)).build());
        list.add(MoimDetail.builder().moim(moim2).detailDesc("데이팅~").moimImg("https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png").fee(20000).offAddr("서울").online(onlineList.get(0)).category(categoryList.get(2)).build());
        list.add(MoimDetail.builder().moim(moim3).detailDesc("스프링~").moimImg("https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png").fee(10000).offAddr("경기").online(onlineList.get(0)).studyCategory(studyCategoryList.get(0)).build());
        list.add(MoimDetail.builder().moim(moim4).detailDesc("파이썬~").moimImg("https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png").fee(10000).offAddr("서울").online(onlineList.get(0)).studyCategory(studyCategoryList.get(1)).build());
        list.add(MoimDetail.builder().moim(moim5).detailDesc("옷 쇼핑몰~").moimImg("https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png").fee(20000).offAddr("부산").online(onlineList.get(0)).category(categoryList.get(5)).build());
        list.add(MoimDetail.builder().moim(moim6).detailDesc("헬스앱~").moimImg("https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png").fee(20000).offAddr("서울").online(onlineList.get(0)).category(categoryList.get(9)).build());
        list.add(MoimDetail.builder().moim(moim7).detailDesc("코테~").moimImg("https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png").fee(10000).offAddr("경기").online(onlineList.get(1)).studyCategory(studyCategoryList.get(2)).build());
        list.add(MoimDetail.builder().moim(moim8).detailDesc("SQL~").moimImg("https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png").fee(10000).offAddr("서울").online(onlineList.get(2)).studyCategory(studyCategoryList.get(0)).build());
        list.add(MoimDetail.builder().moim(moim9).detailDesc("인공지능~").moimImg("https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png").fee(20000).offAddr("대구").online(onlineList.get(0)).category(categoryList.get(11)).build());
        list.add(MoimDetail.builder().moim(moim10).detailDesc("SNS~").moimImg("https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png").fee(20000).offAddr("강릉").online(onlineList.get(0)).category(categoryList.get(4)).build());
        list.forEach(moimDetail -> moimDetailRepository.save(moimDetail));
    }

    @Test
    void insertOne(){
        onlineList = onlineRepository.findAll();
        categoryList = categoryRepository.findAll();
        studyCategoryList = studyCategoryRepository.findAll();

        Users user = Users.builder().email("ysm@naver.com").pass("5545").nickname("심온").role("ROLE_USER").build();
        userRepository.save(user);

        Moim moim = Moim.builder().type("proejct").subject("프로젝트7").shortDesc("레시피앱 개발").users(user).build();
        MoimDetail moimDetail = MoimDetail.builder()
                .moim(moim).detailDesc("레시피앱~")
                .moimImg("https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png")
                .fee(10000).offAddr("일산")
                .online(onlineList.get(0))
                .category(categoryList.get(10))
                .build();
        moimDetailRepository.save(moimDetail);
    }

}