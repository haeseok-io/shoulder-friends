package kr.co.shoulf.web.control;

import kr.co.shoulf.web.entity.PositionDetail;
import kr.co.shoulf.web.service.PositionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/position")
@RequiredArgsConstructor
public class PositionController {
    private final PositionDetailService positionDetailService;

    @GetMapping(value = "/{positionNo}/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PositionDetail> getPositionDetailList(@PathVariable("positionNo") Integer no) {
        return positionDetailService.readPositionDetail(no);
    }
}
