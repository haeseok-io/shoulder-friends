package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MoimParticipantsRepositoryTest {
    @Autowired
    private MoimParticipantsRepository moimParticipantsRepository;
    @Autowired
    private MoimHeadcountRepository moimHeadcountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MoimRepository moimRepository;

    @Test
    @DisplayName("프로젝트 지원 신청 추가")
    void testInsertMoimParticipants() {
        //ArrayList<MoimParticipants> test = new ArrayList<>();
        //List<MoimHeadcount> moimHeadcounts = moimHeadcountRepository.findAll();
        //List<Users> users = userRepository.findAll();
        //List<Moim> moims = moimRepository.findAll();
        //
        //test.add(MoimParticipants.builder().reason("소셜앱개발하고싶어서").job("Y").status(3).users(users.get(0)).moimHeadcount(moimHeadcounts.get(16)).moim(moims.get(9)).build());
        //test.add(MoimParticipants.builder().reason("인공지능 앱 개발해보고싶어서").job("Y").status(1).users(users.get(1)).moimHeadcount(moimHeadcounts.get(13)).moim(moims.get(8)).build());
        //test.add(MoimParticipants.builder().reason("SQL에 대해 더 배우고싶어서").job("N").status(1).users(users.get(2)).moimHeadcount(moimHeadcounts.get(12)).moim(moims.get(7)).build());
        //test.add(MoimParticipants.builder().reason("코테 준비하고싶어서").job("N").status(1).users(users.get(3)).moimHeadcount(moimHeadcounts.get(11)).moim(moims.get(6)).build());
        //test.add(MoimParticipants.builder().reason("헬스앱 개발해보고싶어서").job("Y").status(1).users(users.get(4)).moimHeadcount(moimHeadcounts.get(10)).moim(moims.get(5)).build());
        //test.add(MoimParticipants.builder().reason("쇼핑몰 개발해보고 싶어서").job("Y").status(1).users(users.get(5)).moimHeadcount(moimHeadcounts.get(9)).moim(moims.get(4)).build());
        //test.add(MoimParticipants.builder().reason("파이썬 배우고 싶어서").job("N").status(1).users(users.get(6)).moimHeadcount(moimHeadcounts.get(8)).moim(moims.get(3)).build());
        //test.add(MoimParticipants.builder().reason("스프링 더 배우고 싶어서").job("N").status(1).users(users.get(7)).moimHeadcount(moimHeadcounts.get(7)).moim(moims.get(2)).build());
        //test.add(MoimParticipants.builder().reason("데이팅 앱을 개발해보고 싶어서").job("Y").status(1).users(users.get(8)).moimHeadcount(moimHeadcounts.get(4)).moim(moims.get(1)).build());
        //test.add(MoimParticipants.builder().reason("애견앱 개발해보고싶어서").job("Y").status(2).users(users.get(9)).moimHeadcount(moimHeadcounts.get(3)).moim(moims.get(0)).build());
        //test.add(MoimParticipants.builder().reason("애견앱 개발해보고싶어서").job("N").status(2).users(users.get(8)).moimHeadcount(moimHeadcounts.get(2)).moim(moims.get(0)).build());
        //
        //test.forEach(moimParticipants -> moimParticipantsRepository.save(moimParticipants));
    }
}