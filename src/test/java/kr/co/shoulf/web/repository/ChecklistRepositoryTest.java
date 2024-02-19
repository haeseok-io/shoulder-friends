package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Checklist;
import kr.co.shoulf.web.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
@SpringBootTest
@Slf4j
class ChecklistRepositoryTest {
    @Autowired
    ChecklistRepository checklistRepository;
    @Autowired
    UserRepository userRepository;
    @Test
    void insertData(){
        Optional<Users> user1 = userRepository.findById(1L);
        Optional<Users> user9 = userRepository.findById(9L);
        checklistRepository.save(Checklist.builder().contents("").status(2).users(user1.get()).build());

    }
}