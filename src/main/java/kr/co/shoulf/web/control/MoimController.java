package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.dto.*;
import kr.co.shoulf.web.entity.MoimParticipants;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private final UserJobService userJobService;
    private final ChecklistService checklistService;
    private final MeetingService meetingService;
    private final UserOnlineService userOnlineService;

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

    @PostMapping("/apply")
    public String applyMoim(@RequestParam("positionDetailNo")Integer positionDetailNo,
                          @RequestParam("moimHeadcountNo") Long moimHeadcountNo,
                          @RequestParam("moimNo") Long moimNo,
                          @ModelAttribute MoimParticipants moimParticipants,
                          HttpSession session){
        moimService.applyParticipant(positionDetailNo, moimHeadcountNo, session, moimParticipants);
        System.out.println(moimParticipants);
        return "redirect:/moim/detail?no=" + moimNo;
    }
    @PostMapping("/approveParticipant")
    public String approveParticipant(@RequestParam("moimNo") Long moimNo,
                                     @RequestParam("participantNo") Long participantNo){
        moimService.approveParticipant(participantNo);
        return "redirect:/moim/detail?no=" + moimNo;
    }
    @PostMapping("/rejectParticipant")
    public String rejectParticipant(@RequestParam("participantNo") Long participantNo,
                                    @RequestParam("contents") String contents,
                                    @RequestParam("moimNo") Long moimNo){
        moimService.rejectParticipant(participantNo,contents);
        return "redirect:/moim/detail?no=" + moimNo;
    }

    @RequestMapping("/delete")
    public String moimDelete(@RequestParam("no") Long moimNo){
        moimService.deleteOne(moimNo);
        return "redirect:/moim/";
    }
    @RequestMapping("/complete")
    public String moimComplete(@RequestParam("no") Long moimNo){
        moimService.complete(moimNo);
        return "redirect:/moim/detail?no=" + moimNo;
    }

    @RequestMapping("/detail")
    public String detail(@RequestParam("no") Long moimNo, @RequestParam(value = "type", required = false, defaultValue = "info") String type, Model model, HttpSession session){
        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
        MoimDTO moimDTO = moimService.readOne(moimNo);
        String br = System.getProperty("line.separator").toString();
        model.addAttribute( "nlString", br);

        // 자물쇠 검증
        boolean ableControlTab;
        boolean ableMemberTab;
        List<UserDTO> moimMember = new ArrayList<>();
        Users leader = moimDTO.getUsers();
        // 리더 멤버에 추가
        moimMember.add(UserDTO.builder()
                 .userNo(leader.getUserNo())
                 .nickname(leader.getNickname())
                 .userDetail(leader.getUserDetail())
                 .userJob(userJobService.readById(leader))
                 .build());
        //모임 멤버 구해오기
        moimDTO.getHeadcountList().forEach(h->{
            h.getParticipantApprovalList().forEach(p-> {
                moimMember.add(UserDTO.builder()
                        .userNo(p.getUsers().getUserNo())
                        .nickname(p.getUsers().getNickname())
                        .userDetail(p.getUsers().getUserDetail())
                        .userJob(userJobService.readById(p.getUsers()))
                        .build());
            });
        });
        model.addAttribute("moimMember", moimMember);

        // 지원자 읽어오기
        List<MoimParticipantDTO> participants = new ArrayList<>();
        moimDTO.getHeadcountList().forEach(h ->{
            h.getParticipantList().forEach(p->{
                participants.add(
                        MoimParticipantDTO.builder()
                                .moimParticipantsNo(p.getMoimParticipantsNo())
                                .userJob(userJobService.readById(p.getUsers()))
                                .userOnline(userOnlineService.readOne(p.getUsers()))
                                .users(p.getUsers())
                                .job(p.getJob())
                                .status(p.getStatus())
                                .reason(p.getReason())
                                .moimHeadcount(p.getMoimHeadcount())
                                .build());
            });
        });
        model.addAttribute("participants", participants);
        if(loggedInUser != null){
            //관리 탭
            ableControlTab = Objects.equals(loggedInUser.getUserNo(), moimDTO.getUsers().getUserNo());
            model.addAttribute("ableControlTab",ableControlTab);
            // 할일, 미팅 탭
            ableMemberTab = moimMember.stream().anyMatch(member -> Objects.equals(member.getUserNo(), loggedInUser.getUserNo()));
            model.addAttribute("ableMemberTab", ableMemberTab);
        }
        // 탭 클릭별 보여줄 것
        if(type != null){
            model.addAttribute("type", type);
            //정보 탭 클릭시
            if(type.equals("info")){
                model.addAttribute("infoTab", true);
            } else if (type.equals("member")){
                model.addAttribute("memberTab", true);
            } else if (type.equals("todo")){
                model.addAttribute("todoTab", true);
                model.addAttribute("list", checklistService.getlist(moimNo));
                model.addAttribute("moimUser", moimService.readOne(moimNo).getUsers());
                model.addAttribute("endlist", checklistService.getEndlist(moimNo));
                model.addAttribute("moimNo", moimNo);
            } else if (type.equals("meeting")) {
                model.addAttribute("meetingTab", true);
                model.addAttribute("meetinglist", meetingService.meetingList(moimNo));
                model.addAttribute("moimUser", moimService.readOne(moimNo).getUsers());
                model.addAttribute("moimNo", moimNo);
            } else if (type.equals("control")){
                model.addAttribute("controlTab", true);
            }
        }


        model.addAttribute("moim", moimDTO);
        return "moim/detail";
    }
}
