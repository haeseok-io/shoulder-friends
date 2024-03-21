package kr.co.shoulf.web.service;

import jakarta.websocket.Session;
import kr.co.shoulf.web.entity.Checklist;
import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.repository.ChecklistRepository;
import kr.co.shoulf.web.repository.MoimRepository;
import kr.co.shoulf.web.repository.UserRepository;
import kr.co.shoulf.web.security.custom.userDetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    //완료 상태인 체크리스트 찾기
    public Object getEndlist(Long moimNo) {
        List<Checklist> checklistList = checklistRepository.findByMoim_MoimNoAndStatus(moimNo, 2);
        return checklistList;
    }

    //체크리스트 등록
    public void checklistWrite(String contents, Long moimNo, Users loggedInUser) {
        Optional<Moim> result = moimRepository.findById(moimNo);
        Moim moim = result.orElseThrow();

        //현재 로그인한 회원
        Users users = userRepository.findByEmail(loggedInUser.getEmail());

        Checklist checklist = Checklist.builder()
                .contents(contents)
                .moim(moim)
                .users(users)
                .build();
        checklistRepository.save(checklist);
    }

    //체크리스트 미완료
    public void checkdo(Long checklistNo) {
        Optional<Checklist> result = checklistRepository.findById(checklistNo);
        Checklist checklist = result.orElseThrow();

        checklist.setStatus(1);

        checklistRepository.save(checklist);
    }

    //체크리스트 완료
    public void checkdone(Long checklistNo) {
        Optional<Checklist> result = checklistRepository.findById(checklistNo);
        Checklist checklist = result.orElseThrow();

        checklist.setStatus(2);

        checklistRepository.save(checklist);
    }

    //체크리스트 삭제
    public void deleteChecklist(Long checklistNo) {
        checklistRepository.deleteById(checklistNo);
    }
}
