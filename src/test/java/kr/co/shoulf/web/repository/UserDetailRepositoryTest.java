package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.UserDetail;
import kr.co.shoulf.web.entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;

    @Test
    @DisplayName("회원 상세 테스트 데이터 추가")
    void testInsertUserDetail() {
        List<Users> userList = userRepository.findAll();
        List<String> introduceList = new ArrayList<>();
        List<String> preferAreaList = new ArrayList<>();
        List<String> profileImgList = new ArrayList<>();
        List<String> gitLinkList = new ArrayList<>();
        List<String> blogLinkList = new ArrayList<>();

        introduceList.add("안녕하세요 커리어 관리 플랫폼 기획 중인 홍길동 입니다.");
        introduceList.add("안녕하세요 현재 7년차 개발자입니다.");
        introduceList.add("데이터와 소통을 통해 문제해결을 즐기는 개발자 안정환입니다.");
        introduceList.add("다양한 협업 툴로 협업을 즐기는 소통형 개발자입니다.");
        introduceList.add("사이드프로젝트를 통해 여러가지를 배우고 있습니다.");
        introduceList.add("신입 웹 개발자를 희망하는 박지성입니다.");
        introduceList.add("공감을 이끄는 디자이너 조규성입니다 ");
        introduceList.add("자바개발자입니다. 잘부탁드립니다");
        introduceList.add("안녕하세요 성장 진행형 개발자입니다.");
        introduceList.add("안녕하세요 3년차 백엔드 개발자입니다.");

        preferAreaList.add("서울특별시");
        preferAreaList.add("서울특별시");
        preferAreaList.add("경기도");
        preferAreaList.add("상관없음");
        preferAreaList.add("상관없음");
        preferAreaList.add("서울특별시");
        preferAreaList.add("대구광역시");
        preferAreaList.add("경기도");
        preferAreaList.add("상관없음");
        preferAreaList.add("상관없음");

        profileImgList.add("https://letspl.me/assets/images/prof-no-img.png");
        profileImgList.add("https://letspl.me/assets/images/prof-no-img.png");
        profileImgList.add("https://letspl.me/assets/images/prof-no-img.png");
        profileImgList.add("https://letspl.me/assets/images/prof-no-img.png");
        profileImgList.add("https://letspl.me/assets/images/prof-no-img.png");
        profileImgList.add("https://letspl.me/assets/images/prof-no-img.png");
        profileImgList.add("https://letspl.me/assets/images/prof-no-img.png");
        profileImgList.add("https://letspl.me/assets/images/prof-no-img.png");
        profileImgList.add("https://letspl.me/assets/images/prof-no-img.png");
        profileImgList.add("https://letspl.me/assets/images/prof-no-img.png");

        gitLinkList.add("https://github.com/aaa");
        gitLinkList.add("https://github.com/bbb");
        gitLinkList.add("https://github.com/ccc");
        gitLinkList.add("https://github.com/ddd");
        gitLinkList.add("https://github.com/eee");
        gitLinkList.add("https://github.com/fff");
        gitLinkList.add("https://github.com/ggg");
        gitLinkList.add("https://github.com/hhh");
        gitLinkList.add("https://github.com/iii");
        gitLinkList.add("https://github.com/jjj");

        blogLinkList.add("https://blog.naver.com/aaa");
        blogLinkList.add("https://blog.naver.com/bbb");
        blogLinkList.add("https://blog.naver.com/ccc");
        blogLinkList.add("https://blog.naver.com/ddd");
        blogLinkList.add("https://blog.naver.com/eee");
        blogLinkList.add("https://blog.naver.com/fff");
        blogLinkList.add("https://blog.naver.com/ggg");
        blogLinkList.add("https://blog.naver.com/hhh");
        blogLinkList.add("https://blog.naver.com/iii");
        blogLinkList.add("https://blog.naver.com/jjj");

        // 회원과 매칭하여 저장
        AtomicInteger index = new AtomicInteger();
        userList.forEach(user -> {
            int dex = index.getAndIncrement();

            System.out.println(user);
            userDetailRepository.save(UserDetail.builder()
                            .userNo(user.getUserNo())
                            .introduce(introduceList.get(dex))
                            .preferArea(preferAreaList.get(dex))
                            .profileImg(profileImgList.get(dex))
                            .gitLink(gitLinkList.get(dex))
                            .blogLink(blogLinkList.get(dex))
                            .users(user)
                            .build()
            );
        });
    }
}