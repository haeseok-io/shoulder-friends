package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Checklist;
import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@SpringBootTest
@Slf4j
class ChecklistRepositoryTest {
    @Autowired
    ChecklistRepository checklistRepository;
    @Autowired
    MoimRepository moimRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void insertData(){
        Optional<Users> user1 = userRepository.findById(1L);
        Optional<Users> user9 = userRepository.findById(9L);
        Optional<Moim> moim = moimRepository.findById(1L);
        List<Checklist> list = new ArrayList<>();
        list.add(Checklist.builder().contents("와이어프레임").status(2).moim(moim.get()).users(user1.get()).build());
        list.add(Checklist.builder().contents("데이터베이스 설계").status(1).moim(moim.get()).users(user1.get()).build());
        list.add(Checklist.builder().contents("웹서버 구축").status(1).moim(moim.get()).users(user9.get()).build());
        list.forEach(checklist -> checklistRepository.save(checklist));
    }
}