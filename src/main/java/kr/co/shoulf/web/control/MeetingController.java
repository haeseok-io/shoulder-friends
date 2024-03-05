package kr.co.shoulf.web.control;

import kr.co.shoulf.web.service.MeetingService;
import kr.co.shoulf.web.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    }

    //카페 목록 페이지로 이동
    @GetMapping("/cafelist")
    public void cafelist(Model model, @RequestParam String checkin){
        model.addAttribute("checkin", checkin);
        model.addAttribute("cafeList",studyService.listCafe());
    }

    //룸 목록 페이지로 이동
    @GetMapping("/roomlist")
    public void roomlist(Model model, @RequestParam String checkin, @RequestParam Long studycafeNo){
        model.addAttribute("roomImgList",studyService.ckeckinlistRoomImg(studycafeNo, checkin));
        model.addAttribute("studycafeNo", studycafeNo);
        model.addAttribute("listRoom", studyService.ckeckinlistRoom(studycafeNo, checkin));
        model.addAttribute("oneCafe", studyService.oneCafe(studycafeNo));
        model.addAttribute("checkin", checkin);
    }
}
