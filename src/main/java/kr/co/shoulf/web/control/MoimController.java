package kr.co.shoulf.web.control;

import kr.co.shoulf.web.dto.MoimDTO;
import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.service.MoimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/moim")
public class MoimController {
    private final MoimService moimService;

    @GetMapping("/list")
    public String moimList(Model model){
        List<Moim> moimList = moimService.readAll();
        List<MoimDTO> newMoims = moimService.readNewMoim();
        model.addAttribute("moimList", moimList);
        model.addAttribute("newMoims", newMoims);
        System.out.println(moimList);
        System.out.println(newMoims);
        return "moim/list";
    }

    @GetMapping("/detail/{no}")
    public String moimDetail(@PathVariable("no") Long moimNo){
//        MoimDetailDTO

        return null;
    }

}
