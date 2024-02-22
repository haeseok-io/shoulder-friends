package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

//    private final UserRepository userRepository;
//    private final MemberAnnualRepository memberAnnualRepository;
//    private final StudyroomImageRepository studyroomImageRepository;
//
//    public HelloController(UserRepository userRepository, MemberAnnualRepository memberAnnualRepository, StudyroomImageRepository studyroomImageRepository) {
//        this.userRepository = userRepository;
//        this.memberAnnualRepository = memberAnnualRepository;
//        this.studyroomImageRepository = studyroomImageRepository;
//    }

    @GetMapping(value = {"/"})
    public String test(HttpSession session, Model model) {
        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("users", loggedInUser);
            return "login/test";
        } else {
            // 로그인되지 않은 경우 로그인 페이지로 이동
            return "redirect:/login";
        }
    }

//    @GetMapping(value = {"/hello"})
//    public String hello(Model model) {
//        List<Users> usersList = userRepository.findAll();
//        System.out.println("목록 : " + usersList);
//        model.addAttribute("mList", usersList);
//        return "hello";
//    }
//
//    @GetMapping(value = {"/hello2"})
//    public String hello2(Model model) {
//        List<MemberAnnual> memberAnnualList = memberAnnualRepository.findAll();
////        Optional<StudyroomImage> studyroomImage1 = studyroomImageRepository.findByStudyroomImageNo(39L);
////        Optional<StudyroomImage> studyroomImage2 = studyroomImageRepository.findByStudyroomImageNo(58L);
//
//        System.out.println("목록 : " + memberAnnualList);
////        System.out.println("img1 : " + studyroomImage1);
////        System.out.println("img2 : " + studyroomImage2);
//
////        String imagePath1 = studyroomImage1.map(StudyroomImage::getPath).orElse("Image Not Found");
////        String imagePath2 = studyroomImage2.map(StudyroomImage::getPath).orElse("Image Not Found");
//
//        model.addAttribute("maList", memberAnnualList);
////        model.addAttribute("img1", imagePath1);
////        model.addAttribute("img2", imagePath2);
//
//        return "hello2";
//    }
}
