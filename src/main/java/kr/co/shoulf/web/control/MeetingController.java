package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.shoulf.web.dto.KakaopayDTO;
import kr.co.shoulf.web.dto.MeetingDTO;
import kr.co.shoulf.web.dto.ReservPaymentDTO;
import kr.co.shoulf.web.service.MeetingService;
import kr.co.shoulf.web.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {
    private final MeetingService meetingService;
    private final StudyService studyService;

    // 미팅 디테일로 이동
    @GetMapping("/detail")
    public void detail(Model model, @RequestParam Long moimNo, Authentication authentication){
        model.addAttribute("meetinglist", meetingService.meetingList(moimNo));
        model.addAttribute("moimNo", moimNo);
//        model.addAttribute("userList", meetingService.getUsers(authentication));
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

    //미팅 카카오페이 결제 요청
    @PostMapping("/kakaopaypayment")
    public ResponseEntity<String> kakaopaypayment(ReservPaymentDTO reservPaymentDTO){
        System.out.println(reservPaymentDTO);
        meetingService.kakaoPay(reservPaymentDTO);
        return ResponseEntity.ok(" ");
    }

    @GetMapping("/kakaosuccess")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken) {

        KakaopayDTO kakaopayDTO = meetingService.approveResponse(pgToken);

        return new ResponseEntity<>(kakaopayDTO, HttpStatus.OK);
    }

//    @GetMapping("/kakaocancel")
//    public void cancel() {
//        throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
//    }
//
//    @GetMapping("/kakaofail")
//    public void fail() {
//        throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
//    }

}
