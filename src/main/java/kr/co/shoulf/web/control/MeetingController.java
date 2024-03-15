package kr.co.shoulf.web.control;

import jakarta.servlet.http.PushBuilder;
import kr.co.shoulf.web.dto.MeetingDTO;
import kr.co.shoulf.web.dto.ReservPaymentDTO;
import kr.co.shoulf.web.service.MeetingService;
import kr.co.shoulf.web.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.validator.PublicClassValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {
    private final MeetingService meetingService;
    private final StudyService studyService;

    // 미팅 디테일로 이동
    @GetMapping("/detail")
    public void detail(Model model, @RequestParam Long moimNo){
        model.addAttribute("meetinglist", meetingService.meetingList(moimNo));
        model.addAttribute("moimNo", moimNo);
    }

    //카페 목록 페이지로 이동
    @GetMapping("/cafelist")
    public void cafelist(Model model, @RequestParam String checkin, @RequestParam Long moimNo){
        model.addAttribute("checkin", checkin);
        model.addAttribute("moimNo", moimNo);
        model.addAttribute("cafeList",studyService.listCafe());
    }

    //룸 목록 페이지로 이동
    @GetMapping("/roomlist")
    public void roomlist(Model model, @RequestParam String checkin, @RequestParam Long studycafeNo, @RequestParam Long moimNo){
        model.addAttribute("roomImgList",studyService.ckeckinlistRoomImg(studycafeNo, checkin));
        model.addAttribute("studycafeNo", studycafeNo);
        model.addAttribute("listRoom", studyService.ckeckinlistRoom(studycafeNo, checkin));
        model.addAttribute("oneCafe", studyService.oneCafe(studycafeNo));
        model.addAttribute("checkin", checkin);
        model.addAttribute("moimNo", moimNo);
    }

    //meeting_ajax 처리
    @GetMapping("/meeting_ajax")
    public @ResponseBody List<Map<String,Object>> meeting_ajax(@RequestParam Long moimNo){
        return meetingService.getEventList(moimNo);
    }

    //미팅 정보 받아서 저장
    @PostMapping("/write")
    public ResponseEntity<String> write(@RequestBody MeetingDTO meetingDTO){
        meetingService.writeMeeting(meetingDTO);
        return ResponseEntity.ok(" ");
    }

    //미팅 일반 결제 예약 결제 저장
    @PostMapping("/reservPayment")
    public ResponseEntity<String> reservPayment(ReservPaymentDTO reservPaymentDTO, Authentication authentication){
        System.out.println(reservPaymentDTO);
        meetingService.writeReservPayment(reservPaymentDTO, authentication);
        return ResponseEntity.ok(" ");
    }

    //미팅 정보로 스터디룸 정보 보내기
    @GetMapping("/ckeckmeeting")
    public @ResponseBody Map<String,Object> ckeckmeeting(@RequestParam("moimNo") String moimNo, @RequestParam("startDate") String startDate){
        MeetingDTO meetingDTO = new MeetingDTO();
        meetingDTO.setMoimNo(Long.valueOf(moimNo));
        meetingDTO.setStartDate(startDate);
        return meetingService.getStudyroom(meetingDTO);
    }

    //미팅 정보 수정
    @PostMapping("/modify")
    public String modify(MeetingDTO meetingDTO){
        meetingService.modifyMeeting(meetingDTO);
        return "redirect:/meeting/detail?moimNo="+meetingDTO.getMoimNo();
    }

    //미팅 정보 삭제
    @PostMapping("/delete")
    public String delete(@RequestParam Long moimNo,@RequestParam String meetingdate){
        meetingService.deleteMeeting(moimNo, meetingdate);
        return "redirect:/meeting/detail?moimNo="+moimNo;
    }

    //스터디룸 예약 취소
    @PostMapping("/cancel")
    public String cancel(@RequestParam String endDate, @RequestParam Long roomNo2, @RequestParam Long moimNo){
        meetingService.cancelRoom(endDate, roomNo2);
        return "redirect:/meeting/detail?moimNo="+moimNo;
    }

    //페이 임시 추가
    @GetMapping("/payment")
    public ResponseEntity payment(@RequestParam int amount, @RequestParam String paymentKey, @RequestParam String orderId){
        System.out.println(amount+" : "+paymentKey+" : "+orderId);
        meetingService.payment(amount, paymentKey, orderId);
        return ResponseEntity.ok().build();
    }

}
