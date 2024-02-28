package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    // 관리자 로그인 처리 메서드
    public Member adminLogin(Member member) {
        // 받아온 멤버의 ID로 해당 멤버의 정보 불러오기
        Member readMember = memberRepository.findById(member.getId());

        // 조회된 멤버가 없을 경우 예외 발생
        if (readMember == null) {
            throw new UsernameNotFoundException("가입된 아이디를 찾을 수 없습니다. " + member.getId());
        }

        // 조회된 멤버 정보를 이용하여 새로운 Member 객체 생성하여 반환
        return Member.builder()
                .memberNo(readMember.getMemberNo())
                .id(readMember.getId())
                .pw(readMember.getPw())
                .role(readMember.getRole())
                .name(readMember.getName())
                .post(readMember.getPost())
                .addr(readMember.getAddr())
                .phone(readMember.getPhone())
                .hiredate(readMember.getHiredate())
                .build();
    }

    // 회원 가입 처리 메서드
    public void registerMember(Member member) {
        // 이미 존재하는 회원인지 확인
        boolean isMember = memberRepository.existsById(member.getId());
        if(isMember) {
            return; // 이미 가입된 회원이면 메서드 종료
        }

        // 새로운 회원 객체 생성 및 저장
        Member newMember = new Member();
        newMember.setId(member.getId());
        newMember.setPw(passwordEncoder.encode(member.getPw()));
        newMember.setName(member.getName());
        newMember.setPhone(member.getPhone());
        newMember.setPost(member.getPost());
        newMember.setAddr(member.getAddr());
        newMember.setRole("ROLE_NONE");

        memberRepository.save(newMember);
    }
}
