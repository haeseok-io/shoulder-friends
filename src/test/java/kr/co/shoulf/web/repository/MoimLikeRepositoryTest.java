package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.MoimLike;
import kr.co.shoulf.web.entity.Users;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MoimLikeRepositoryTest {
    @Autowired
    MoimLikeRepository moimLikeRepository;
    @Autowired
    MoimRepository moimRepository;
    @Autowired
    UserRepository userRepository;
    List<Users> usersList;
    List<Moim> moimList;

    @BeforeEach()
    void before() {
        usersList = userRepository.findAll();
        moimList = moimRepository.findAll();

    }

    @Test
    void insertData() {
        List<MoimLike> list = new ArrayList<>();
        //list.add(MoimLike.builder().user(usersList.get(0)).moim(moimList.get(9)).build());
        //list.add(MoimLike.builder().user(usersList.get(1)).moim(moimList.get(8)).build());
        //list.add(MoimLike.builder().user(usersList.get(2)).moim(moimList.get(7)).build());
        //list.add(MoimLike.builder().user(usersList.get(3)).moim(moimList.get(6)).build());
        //list.add(MoimLike.builder().user(usersList.get(4)).moim(moimList.get(5)).build());
        //list.add(MoimLike.builder().user(usersList.get(5)).moim(moimList.get(4)).build());
        //list.add(MoimLike.builder().user(usersList.get(6)).moim(moimList.get(3)).build());
        //list.add(MoimLike.builder().user(usersList.get(7)).moim(moimList.get(2)).build());
        //list.add(MoimLike.builder().user(usersList.get(8)).moim(moimList.get(1)).build());
        //list.add(MoimLike.builder().user(usersList.get(9)).moim(moimList.get(0)).build());
        //list.forEach(moimLike -> moimLikeRepository.save(moimLike));
    }
}