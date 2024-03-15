package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.MemberAnnual;
import kr.co.shoulf.web.entity.MemberAnnualDetail;
import kr.co.shoulf.web.repository.MemberAnnualDetailRepository;
import kr.co.shoulf.web.repository.MemberAnnualRejectedRepository;
import kr.co.shoulf.web.repository.MemberAnnualRepository;
import kr.co.shoulf.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AnnualService {
    private final MemberAnnualRepository memberAnnualRepository;
    private final MemberAnnualDetailRepository memberAnnualDetailRepository;
    private final MemberRepository memberRepository;
    private final MemberAnnualRejectedRepository memberAnnualRejectedRepository;
    private final MemberService memberService;
    public List<MemberAnnualDetail> getAnnualDetailList() {
        return memberAnnualDetailRepository.findAll();
    }

    public List<MemberAnnual> getAnnualListByMemberNo(Long memberNo) {
        return memberAnnualRepository.findByMember_MemberNo(memberNo);
    }

    public void insertMemberAnnual(int type, int num, String reason, Long memberNo) {
        Member member = memberRepository.findById(memberNo).orElse(null);

        MemberAnnual memberAnnual = MemberAnnual.builder()
                .type(type)
                .num(num)
                .reason(reason)
                .member(member)
                .build();

        memberAnnualRepository.save(memberAnnual);
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

    public List<MemberAnnualDetail> totalAnnualNum(Member member, Long memberNo) {
        List<MemberAnnualDetail> memberAnnualDetails = getAnnualDetailListByMemberNo(member, memberNo);

        Map<Long, MemberAnnualDetail> resultMap = new HashMap<>(); // 연차 번호를 키로 사용하여 누적된 결과를 저장하는 맵

        for (MemberAnnualDetail memberAnnualDetail : memberAnnualDetails) {
            Long annualNo = memberAnnualDetail.getMemberAnnual().getAnnualNo();

            // 맵에 해당 연차 번호가 존재하지 않으면 새로운 결과를 추가하고 초기화
            if (!resultMap.containsKey(annualNo)) {
                MemberAnnualDetail result = new MemberAnnualDetail();
                result.setUseNum(0);
                result.setMemberAnnual(memberAnnualDetail.getMemberAnnual());
                resultMap.put(annualNo, result);
            }

            // 현재 연차 상세 정보의 사용갯수를 누적
            MemberAnnualDetail result = resultMap.get(annualNo);
            result.setUseNum(result.getUseNum() + memberAnnualDetail.getUseNum());
        }

        return new ArrayList<>(resultMap.values());
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
