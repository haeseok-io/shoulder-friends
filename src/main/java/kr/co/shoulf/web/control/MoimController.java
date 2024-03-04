package kr.co.shoulf.web.control;

import kr.co.shoulf.web.dto.MoimDataRequestDTO;
import kr.co.shoulf.web.dto.PageRequestDTO;
import kr.co.shoulf.web.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/moim")
public class MoimController {
    private final MoimService moimService;
    private final CategoryService categoryService;
    private final StudyCategoryService studyCategoryService;
    private final PlatformService platformService;
    private final OnlineService onlineService;
    private final PositionService positionService;

    @GetMapping(value = "/")
    public String list(PageRequestDTO pageRequestDto, Model model){
        model.addAttribute("moimNewList", moimService.readNewList());
        model.addAttribute("moimList", moimService.readMoimList(pageRequestDto));

        return "moim/list";
    }

    @GetMapping(value = "/write")
    public String write(Model model) {
        model.addAttribute("categoryList", categoryService.readAll());
        model.addAttribute("studyCategoryList", studyCategoryService.readAll());
        model.addAttribute("platformList", platformService.readAll());
        model.addAttribute("onlineList", onlineService.readAll());
        model.addAttribute("positionList", positionService.readAll());
        return "moim/write";
    }

    @PostMapping(value = "/write")
    public String writeOk(MoimDataRequestDTO moimDataRequestDTO) {
        boolean status = moimService.addOne(moimDataRequestDTO);


        return "redirect:/moim/";
    }
}
