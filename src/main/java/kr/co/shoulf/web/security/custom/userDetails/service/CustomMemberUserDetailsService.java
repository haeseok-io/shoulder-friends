package kr.co.shoulf.web.security.custom.userDetails.service;

import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.repository.MemberRepository;
import kr.co.shoulf.web.security.custom.userDetails.CustomMemberUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomMemberUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username);

        if (member == null) {
            throw new UsernameNotFoundException("아이디 정보가 존재하지 않습니다.");
        }

        return new CustomMemberUserDetails(member);
    }
}
