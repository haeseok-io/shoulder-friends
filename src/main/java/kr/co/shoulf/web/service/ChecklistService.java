package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.Checklist;
import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.repository.ChecklistRepository;
import kr.co.shoulf.web.repository.MoimRepository;
import kr.co.shoulf.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChecklistService {
    private final ChecklistRepository checklistRepository;
    private final MoimRepository moimRepository;
    private final UserRepository userRepository;

    //모임 번호로 체크리스트 찾기
    public List<Checklist> getlist(Long moimNo) {
        List<Checklist> checklistList = checklistRepository.findByMoim_MoimNo(moimNo);
        return checklistList;
    }

    //체크리스트 등록
    public void checklistWrite(String contents, Long moimNo) {
        Optional<Moim> result = moimRepository.findById(moimNo);
        Moim moim = result.orElseThrow();
        
        //현재 로그인한 회원
        Optional<Users> result2 = userRepository.findById(1L);
        Users users = result2.orElseThrow();

        Checklist checklist = Checklist.builder()
                .contents(contents)
                .moim(moim)
                .users(users)
                .build();
        checklistRepository.save(checklist);
    }
}
