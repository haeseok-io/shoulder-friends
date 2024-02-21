package kr.co.shoulf.web.control;

import kr.co.shoulf.web.entity.Moim;
import kr.co.shoulf.web.entity.MoimDetail;
import kr.co.shoulf.web.repository.MoimDetailRepository;
import kr.co.shoulf.web.repository.MoimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MoimController {
    private final MoimRepository moimRepository;

    @GetMapping("/moim")
    public String moimList(Model model){
        List<Moim> moimList = moimRepository.findAll().stream().toList();
        model.addAttribute("moimList", moimList);
        System.out.println(moimList);
        return "moimList";
    }
}
