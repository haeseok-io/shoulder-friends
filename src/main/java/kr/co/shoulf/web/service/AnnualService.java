package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.MemberAnnual;
import kr.co.shoulf.web.entity.MemberAnnualDetail;
import kr.co.shoulf.web.repository.MemberAnnualDetailRepository;
import kr.co.shoulf.web.repository.MemberAnnualRejectedRepository;
import kr.co.shoulf.web.repository.MemberAnnualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AnnualService {
    private final MemberAnnualRepository memberAnnualRepository;
    private final MemberAnnualDetailRepository memberAnnualDetailRepository;
    private final MemberAnnualRejectedRepository memberAnnualRejectedRepository;
    private final MemberService memberService;
    public List<MemberAnnualDetail> getAnnualDetailList() {
        return memberAnnualDetailRepository.findAll();
    }

    public List<MemberAnnual> getAnnualListByMemberNo(Long memberNo) {
        return memberAnnualRepository.findByMember_MemberNo(memberNo);
    }
    public List<MemberAnnualDetail> getAnnualDetailListByMemberNo(Member member, Long memberNo) {
        List<MemberAnnualDetail> memberAnnualDetails = memberAnnualDetailRepository.findByMemberAnnual_MemberAndMember_MemberNo(member, memberNo);

        List<MemberAnnualDetail> memberAnnualDetailList = new ArrayList<>();

        for (MemberAnnualDetail memberAnnualDetail : memberAnnualDetails) {
            if (memberAnnualDetail.getStatus() == 1 ||
                memberAnnualDetail.getStatus() == 3) {

                memberAnnualDetail.setUseNum(0);

            }else if(memberAnnualDetail.getStatus() == 2) {
                LocalDate endDate = LocalDate.parse(memberAnnualDetail.getEndDate());
                LocalDate startDate = LocalDate.parse(memberAnnualDetail.getStartDate());

                int useNum = (int) ((ChronoUnit.DAYS.between(endDate, startDate) * -1) + 1);

                memberAnnualDetail.setUseNum(useNum);
            }
                memberAnnualDetailList.add(memberAnnualDetail);
        }
        return memberAnnualDetailList;
    }

    public void insertAnnualDetail(Member member, int useCate, String startDate, String endDate, Long annualNo){
        LocalDate parseEndDate = LocalDate.parse(endDate);
        LocalDate parseStartDate = LocalDate.parse(startDate);

        int useNum = (int) ChronoUnit.DAYS.between(parseStartDate, parseEndDate)+1;

        MemberAnnualDetail memberAnnualDetail = MemberAnnualDetail.builder()
                .memberAnnual(memberAnnualRepository.findByAnnualNo(annualNo))
                .member(member)
                .useCate(useCate)
                .useNum(useNum)
                .startDate(startDate)
                .endDate(endDate)
                .status(1)
                .build();

        memberAnnualDetailRepository.save(memberAnnualDetail);
    }

    public MemberAnnualDetail totalAnnualNum(Member member, Long memberNo) {
        List<MemberAnnualDetail> memberAnnualDetails = memberAnnualDetailRepository.findByMemberAnnual_MemberAndMember_MemberNo(member, memberNo);
        MemberAnnualDetail result = null;
        Set<Long> annualNos = new HashSet<>();

        for (MemberAnnualDetail memberAnnualDetail : memberAnnualDetails) {
            if (!annualNos.contains(memberAnnualDetail.getMemberAnnual().getAnnualNo())) {
                annualNos.add(memberAnnualDetail.getMemberAnnual().getAnnualNo());
                if (result == null) {
                    result = memberAnnualDetail;
                }else {
                    result.getMemberAnnual().setNum(result.getMemberAnnual().getNum() + memberAnnualDetail.getMemberAnnual().getNum());
                    result.setUseNum(result.getUseNum() + memberAnnualDetail.getUseNum());
                }
            }
        }

        return result;
    }

    public void approveAnnual(Long annualDetailNo,Long memberNo) {
        MemberAnnualDetail memberAnnualDetail = memberAnnualDetailRepository.findByAnnualDetailNoAndMember_MemberNo(annualDetailNo, memberNo);

        if (memberAnnualDetail != null) {
            memberAnnualDetail.setStatus(2);
            memberAnnualDetailRepository.save(memberAnnualDetail);
        }else {
            System.out.println("승인 실패");
        }
    }

    public void rejectAnnual(Long annualDetailNo,Long memberNo) {
        MemberAnnualDetail memberAnnualDetail = memberAnnualDetailRepository.findByAnnualDetailNoAndMember_MemberNo(annualDetailNo, memberNo);

        if (memberAnnualDetail != null) {
            memberAnnualDetail.setStatus(3);
            memberAnnualDetailRepository.save(memberAnnualDetail);
        }else {
            System.out.println("반려 실패");
        }
    }
}
