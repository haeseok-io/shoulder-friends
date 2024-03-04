package kr.co.shoulf.web.control;

import kr.co.shoulf.web.service.MeetingService;
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

    @GetMapping("/home")
    public void home(){

    }

    @GetMapping("/detail")
    public void detail(Model model, @RequestParam Long moimNo){
        model.addAttribute("meetinglist", meetingService.meetingList(moimNo));
    }
}
