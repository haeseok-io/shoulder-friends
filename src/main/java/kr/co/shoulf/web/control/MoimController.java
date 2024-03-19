package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import kr.co.shoulf.web.dto.*;
import kr.co.shoulf.web.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = {"/", "/list"})
    public String list(PageRequestDTO pageRequestDto, Model model){
        model.addAttribute("moimNewList", moimService.readNewList());
        model.addAttribute("positionList", positionService.readAll());
        return "moim/list";
    }

    @GetMapping(value = "/list/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PageResponseDTO<MoimDTO>> listJsonData(MoimListRequestDTO moimListRequestDTO) {
        return ResponseEntity.ok().body(moimService.readMoimList(moimListRequestDTO));
    }


    @GetMapping(value = "/write")
    public String write(Model model) {
        model.addAttribute("categoryList", categoryService.readAll());
        model.addAttribute("studyCategoryList", studyCategoryService.readAll());
        model.addAttribute("platformList", platformService.readAll());
        model.addAttribute("onlineList", onlineService.readAll());
        model.addAttribute("positionList", positionService.readAll());

        model.addAttribute("processType", "write");
        return "moim/write";
    }

    @PostMapping(value = "/write")
    public String writeOk(MoimDataRequestDTO moimDataRequestDTO, HttpSession session) {
        boolean status = moimService.addOne(moimDataRequestDTO, session);
        return "redirect:/moim/";
    }

    @GetMapping(value = "/modify")
    public String modify(@RequestParam("no") Long moimNo, Model model) {
        model.addAttribute("categoryList", categoryService.readAll());
        model.addAttribute("studyCategoryList", studyCategoryService.readAll());
        model.addAttribute("platformList", platformService.readAll());
        model.addAttribute("onlineList", onlineService.readAll());
        model.addAttribute("positionList", positionService.readAll());

        model.addAttribute("processType", "modify");
        model.addAttribute("moimData", moimService.readModifyOne(moimNo));
        return "moim/write";
    }

    @PostMapping(value = "/modify")
    public String modifyOk(MoimDataRequestDTO moimDataRequestDTO, HttpSession session) {
        moimService.addOne(moimDataRequestDTO, session);
        return "redirect:/moim/";
    }

    @RequestMapping("/detail")
    public String detail(@RequestParam("no") Long moimNo, Model model){
        model.addAttribute("moim", moimService.readOne(moimNo));
        return "moim/detail";
    }
}
