package kr.co.shoulf.web.control;

import kr.co.shoulf.web.dto.PageRequestDTO;
import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.service.MoimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/moim")
public class MoimController {
    private final MoimService moimService;

    @GetMapping("/")
    public String list(PageRequestDTO pageRequestDto, Model model){
        model.addAttribute("moimNewList", moimService.readNewList());
        model.addAttribute("moimList", moimService.readMoimList(pageRequestDto));

        return "moim/list";
    }
}
