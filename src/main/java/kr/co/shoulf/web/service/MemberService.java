package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> memberList() {
        List<Member> members = memberRepository.findAll();
        List<Member> memberList = new ArrayList<>();

        for (Member member : members) {
             Member readMember = Member.builder()
                    .memberNo(member.getMemberNo())
                    .name(member.getName())
                    .phone(member.getPhone())
                    .role(member.getRole())
                    .build();

             memberList.add(readMember);
        }

        return memberList;
    }

    public Member loginMember(String id) {
        return memberRepository.findById(id);
    }
}
