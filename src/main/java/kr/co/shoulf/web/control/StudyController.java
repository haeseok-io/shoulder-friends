package kr.co.shoulf.web.control;

import kr.co.shoulf.web.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Modifier;

@Controller
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {
    private final StudyService studyService;

    @GetMapping("/cafe_list")
    public void cafe_list(Model model){
        model.addAttribute("listCafe", studyService.listCafe());
    }

    @GetMapping("/cafe_write")
    public void cafe_write(){

    }

    @PostMapping("/cafe_write")
    public String cafe_writeOk(){
        return "redirect:/study/cafe_list";
    }

    @GetMapping("/cafe_modify")
    public void cafe_modify(Model model, @RequestParam Long studycafeNo){
        model.addAttribute("oneCafe",studyService.oneCafe(studycafeNo));
    }

    @PostMapping("/cafe_modify")
    public String cafe_modifyOk(){
        return "redirect:/study/cafe_list";
    }

    @PostMapping("/cafe_delete")
    public String deleteCafe(){
        return "redirect:/study/cafe_list";
    }

    @GetMapping("/room_list")
    public void room_list(Model model, @RequestParam Long studycafeNo){
        model.addAttribute("roomImgList",studyService.listRoomImg(studycafeNo));
        model.addAttribute("studycafeNo", studycafeNo);
        model.addAttribute("listRoom", studyService.listRoom(studycafeNo));
        model.addAttribute("oneCafe", studyService.oneCafe(studycafeNo));
    }

    @GetMapping("/room_write")
    public void room_write(){

    }

    @PostMapping("/room_write")
    public String room_writeOk(){
        return "redirect:/study/cafe_list";
    }

    @GetMapping("/room_modify")
    public void room_modify(Model model, @RequestParam Long studyroomNo){
        model.addAttribute("oneRoom",studyService.oneRoom(studyroomNo));
    }

    @PostMapping("/room_modify")
    public String room_modifyOk(){
        return "redirect:/study/cafe_list";
    }

    @PostMapping("/room_delete")
    public String deleteRoom(){
        return "redirect:/study/room_list";
    }
}
