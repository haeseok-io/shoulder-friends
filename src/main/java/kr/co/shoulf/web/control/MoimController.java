package kr.co.shoulf.web.control;

import kr.co.shoulf.web.entity.MoimDetail;
import kr.co.shoulf.web.repository.MoimDetailRepository;
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

    @GetMapping("/list")
    public String moimList(Model model){
        List<MoimDetail> moimList = moimService.getList();
        model.addAttribute("moimList", moimList);
        System.out.println(moimList);
        return "moim/list";
    }
}
