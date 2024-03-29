package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.dto.MeetingDTO;
import kr.co.shoulf.web.dto.ReservPaymentDTO;
import kr.co.shoulf.web.entity.Member;
import kr.co.shoulf.web.entity.Studyroom;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.security.custom.userDetails.CustomUserDetails;
import kr.co.shoulf.web.service.MeetingService;
import kr.co.shoulf.web.service.MoimService;
import kr.co.shoulf.web.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {
    private final MeetingService meetingService;
    private final StudyService studyService;
    private final MoimService moimService;
    public static final String KEY = "0588185562360842";
    public static final String SECRET = "ZODmGjsuy0mz52841UHYk8J4ETFFStAbaoAbtXIcfjb6hnrN3VZHAaHNDwnBzECspEMjQuFYSdlwMRv9";

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
    public ResponseEntity<String> reservPayment(ReservPaymentDTO reservPaymentDTO, HttpSession session) throws IOException {

        List<Studyroom> studyroomList = studyService.ckeckinlistRoom(reservPaymentDTO.getStudycafeNo(), reservPaymentDTO.getCalendar_start_date());
        ArrayList<Long> studyrooms = new ArrayList<>();
        studyroomList.forEach(studyroom -> {
            Long studyroomNo = studyroom.getStudyroomNo();
            studyrooms.add(studyroomNo);
        });

        if (!reservPaymentDTO.getAmount().equals(reservPaymentDTO.getPrice())) {
            String token = meetingService.getToken(KEY, SECRET);
            meetingService.refundRequest(token, String.valueOf(reservPaymentDTO.getApprovalNum()));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
        //현재 남은 방 이면 예약정보 저장 아니면 결제 취소하기
        if(studyrooms.contains(reservPaymentDTO.getStudyroomNo())){
            meetingService.writeReservPayment(reservPaymentDTO, loggedInUser);
        }else {
            String token = meetingService.getToken(KEY, SECRET);
            meetingService.refundRequest(token, String.valueOf(reservPaymentDTO.getApprovalNum()));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();
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
        return "redirect:/moim/detail?no="+meetingDTO.getMoimNo()+"&type=meeting";
    }

    //미팅 정보 삭제
    @PostMapping("/delete")
    public String delete(@RequestParam Long moimNo,@RequestParam String meetingdate){
        meetingService.deleteMeeting(moimNo, meetingdate);
        return "redirect:/moim/detail?no="+moimNo+"&type=meeting";
    }

    //스터디룸 예약 취소
    @PostMapping("/cancel")
    public String cancel(@RequestParam String endDate, @RequestParam Long roomNo2, @RequestParam Long moimNo){
        meetingService.cancelRoom(endDate, roomNo2);
        return "redirect:/moim/detail?no="+moimNo+"&type=meeting";
    }

}
