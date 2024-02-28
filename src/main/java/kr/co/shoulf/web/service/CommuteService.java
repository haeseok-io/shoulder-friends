package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.MemberCommute;
import kr.co.shoulf.web.repository.MemberCommuteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommuteService {
    private final MemberCommuteRepository memberCommuteRepository;
    private final MemberService memberService;
    public List<MemberCommute> readMemberCommuteBySelectedDate(Date selectedDate) {
        return memberCommuteRepository.findByDate(selectedDate);
    }

    public List<MemberCommute> readMemberCommuteList() {
        List<MemberCommute> memberCommutes = memberCommuteRepository.findAll();
        List<MemberCommute> memberCommuteList = new ArrayList<>();

        for (MemberCommute memberCommute : memberCommutes) {
            MemberCommute readMemberCommute = MemberCommute.builder()
                    .commuteNo(memberCommute.getCommuteNo())
                    .date(memberCommute.getDate())
                    .inTime(memberCommute.getInTime())
                    .outTime(memberCommute.getOutTime())
                    .status(memberCommute.getStatus())
                    .member(memberCommute.getMember())
                    .build();

            memberCommuteList.add(readMemberCommute);
        }

        return memberCommuteList;
    }

    public void insertCommuteData() {
        for (Member member : memberService.memberList()) {
            MemberCommute memberCommute = MemberCommute.builder()
                    .member(member)
                    .status("결근")
                    .build();

            memberCommuteRepository.save(memberCommute);
        }
    }

    public void updateInTime(Long memberNo, Date selectedDate) {
        MemberCommute memberCommute = memberCommuteRepository.findByMember_MemberNoAndDate(memberNo, selectedDate);

        LocalDateTime now = LocalDateTime.now();
        Time time = Time.valueOf(now.toLocalTime());
        if (memberCommute.getInTime() == null) {
            if(time.compareTo(Time.valueOf("09:00:00")) < 0) {
                memberCommute.setInTime(time);
                memberCommute.setStatus("정상");
            }else {
                memberCommute.setInTime(time);
                memberCommute.setStatus("지각");
            }
            memberCommuteRepository.save(memberCommute);
        }
    }

    public void updateOutTime(Long memberNo, Date selectedDate) {
        MemberCommute memberCommute = memberCommuteRepository.findByMember_MemberNoAndDate(memberNo, selectedDate);

        LocalDateTime now = LocalDateTime.now();
        Time time = Time.valueOf(now.toLocalTime());

        if (memberCommute.getInTime() != null && memberCommute.getOutTime() == null) {
            if(time.compareTo(Time.valueOf("18:00:00")) < 0) {
                memberCommute.setOutTime(time);
                memberCommute.setStatus("조퇴");
            }else {
                memberCommute.setOutTime(time);
                memberCommute.setStatus("정상");
            }
            memberCommuteRepository.save(memberCommute);
        }
    }
}
