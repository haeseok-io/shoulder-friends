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

    public Member adminLogin(Member member) {
        // 받아온 멤버의 ID로 해당 멤버의 정보 불러오기
        Member readMember = memberRepository.findById(member.getId());
        System.out.println(readMember.getId());

        if (readMember == null) {
            throw new UsernameNotFoundException("가입된 아이디를 찾을 수 없습니다. " + member.getId());
        }

        return Member.builder()
                .id(member.getId())
                .pw(member.getPw())
                .role(member.getRole())
                .build();
    }

    public void registerMember(Member member) {
        boolean isMember = memberRepository.existsById(member.getId());
        if(isMember) {
            return;
        }

        Member newMember = new Member();

        newMember.setId(member.getId());
        newMember.setPw(passwordEncoder.encode(member.getPw()));
        newMember.setName(member.getName());
        newMember.setPhone(member.getPhone());
        newMember.setPost(member.getPost());
        newMember.setAddr(member.getAddr());
        newMember.setRole("ROLE_EMP");

        memberRepository.save(newMember);
    }
}
