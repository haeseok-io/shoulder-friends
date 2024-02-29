package kr.co.shoulf.web.control;

import kr.co.shoulf.web.dto.StudycafeDTO;
import kr.co.shoulf.web.dto.StudyroomDTO;
import kr.co.shoulf.web.entity.*;
import kr.co.shoulf.web.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {
    private final StudyService studyService;
    @Value("D:\\dev\\jworkspace\\shoulder-friends\\src\\main\\resources\\static\\studycafeimg")
    String uploadPath;

    @GetMapping("/alert")
    public void alert(){

    }

    //스터디 카페 리스트
    @GetMapping("/cafe_list")
    public void cafe_list(Model model){
        model.addAttribute("listCafe", studyService.listCafe());
    }

    //스터디 카페 등록 페이지이동
    @GetMapping("/cafe_write")
    public void cafe_write(){

    }

    //스터디카페 등록
    @PostMapping("/cafe_write")
    public String cafe_writeOk(StudycafeDTO dto){

        MultipartFile file = dto.getMainImg();

        //파일 실제 이름
        String originalFilename = file.getOriginalFilename();

        //고유한 이름 만들기
        String uuid = UUID.randomUUID().toString();

        Path savePath = Paths.get(uploadPath, uuid + "_" + originalFilename);

        Studycafe studycafe = Studycafe.builder()
                .name(dto.getName())
                .shortDesc(dto.getShortDesc())
                .post(dto.getPost())
                .addr(dto.getAddr()+" "+dto.getAddrDetail())
                .x(dto.getX())
                .y(dto.getY())
                .mainImg("http://localhost:8081/studycafeimg/"+uuid+"_"+originalFilename)
                .member(dto.getMember())
                .build();
        studyService.saveCafe(studycafe);

        try {
            file.transferTo(savePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/study/cafe_list";
    }

    //스터디 카페 수정 페이지 이동
    @GetMapping("/cafe_modify")
    public void cafe_modify(Model model, @RequestParam Long studycafeNo){
        model.addAttribute("oneCafe",studyService.oneCafe(studycafeNo));
    }

    //스터디 카페 수정
    @PostMapping("/cafe_modify")
    public String cafe_modifyOk(StudycafeDTO dto){

        String address = "";
        if(dto.getAddrDetail() != null || dto.getAddrDetail() != ""){
            address = dto.getAddr() + " " + dto.getAddrDetail();
        }else{
            address = dto.getAddr();
        }

        MultipartFile file = dto.getMainImg();

        if(file.getSize() != 0){

            Studycafe studycafe1 = studyService.oneCafe(dto.getStudycafeNo());
            String cafeimgpath = studycafe1.getMainImg();
            int imgindex = cafeimgpath.lastIndexOf("/");
            String imgname =  cafeimgpath.substring(imgindex+1);

            File files = new File(uploadPath+"\\"+imgname);

            if(files.exists()){
                files.delete();
            }

            //파일 실제 이름
            String originalFilename = file.getOriginalFilename();

            //고유한 이름 만들기
            String uuid = UUID.randomUUID().toString();

            Path savePath = Paths.get(uploadPath, uuid + "_" + originalFilename);

            // 이미지 수정시 스터디 카페 정보 수정
            Studycafe studycafe = Studycafe.builder()
                    .studycafeNo(dto.getStudycafeNo())
                    .name(dto.getName())
                    .shortDesc(dto.getShortDesc())
                    .post(dto.getPost())
                    .addr(address)
                    .x(dto.getX())
                    .y(dto.getY())
                    .mainImg("http://localhost:8081/studycafeimg/"+uuid+"_"+originalFilename)
                    .member(dto.getMember())
                    .build();
            studyService.saveCafe(studycafe);

            try {
                file.transferTo(savePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            // 이미지 수정 안했을때 스터디 카페 정보 수정
            Studycafe result = studyService.oneCafe(dto.getStudycafeNo());
            Studycafe studycafe = Studycafe.builder()
                    .studycafeNo(dto.getStudycafeNo())
                    .name(dto.getName())
                    .shortDesc(dto.getShortDesc())
                    .post(dto.getPost())
                    .addr(address)
                    .x(dto.getX())
                    .y(dto.getY())
                    .mainImg(result.getMainImg())
                    .member(dto.getMember())
                    .build();
            studyService.saveCafe(studycafe);
        }

        return "redirect:/study/room_list?studycafeNo="+dto.getStudycafeNo();
    }

    //카페 삭제
    @PostMapping("/cafe_delete")
    public String deleteCafe(@RequestParam Long studycafeNo, Model model){

        List<Studyroom> studyroom = studyService.listRoom(studycafeNo);

        //스터디룸이 존재하는지 확인
        if(studyroom.isEmpty() == true) {

            Studycafe studycafe1 = studyService.oneCafe(studycafeNo);
            String cafeimgpath = studycafe1.getMainImg();
            int imgindex = cafeimgpath.lastIndexOf("/");
            String imgname = cafeimgpath.substring(imgindex + 1);

            File files = new File(uploadPath + "\\" + imgname);
            if (files.exists()) {
                files.delete();
            }
            studyService.deletecafe(studycafeNo);

        }else if (studyroom.isEmpty() == false){
            model.addAttribute("msg","스터디룸을 먼저 삭제 해주세요");
            model.addAttribute("url","/study/room_list?studycafeNo="+studycafeNo);
            return "/study/alert";
        }
        return "redirect:/study/cafe_list";
    }

    //스터디 카페 디테일 & 스터디룸 목록
    @GetMapping("/room_list")
    public void room_list(Model model, @RequestParam() Long studycafeNo){
        model.addAttribute("roomImgList",studyService.listRoomImg(studycafeNo));
        model.addAttribute("studycafeNo", studycafeNo);
        model.addAttribute("listRoom", studyService.listRoom(studycafeNo));
        model.addAttribute("oneCafe", studyService.oneCafe(studycafeNo));
    }

    //스터디룸 등록 페이지 이동
    @GetMapping("/room_write")
    public void room_write(Model model, @RequestParam Long studycafeNo){
        model.addAttribute("studycafeNo",studycafeNo);
    }

    //스터디룸 등록
    @PostMapping("/room_write")
    public String room_writeOk(StudyroomDTO dto){
        int seq = 1;
        Studycafe studycafe = studyService.oneCafe(dto.getStudycafe().getStudycafeNo());
        Studyroom studyroom = Studyroom.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .studycafe(studycafe)
                .build();
        studyService.saveroom(studyroom);
        StudyroomDetail studyroomDetail = StudyroomDetail.builder()
                .maxNum(dto.getMaxNum())
                .beam(dto.getBeam())
                .wboard(dto.getWboard())
                .socket(dto.getSocket())
                .studyroom(studyroom)
                .build();
        studyService.saveroomDetail(studyroomDetail);

        List<MultipartFile> files = dto.getPath(); //여러개

        for(MultipartFile file : files) {

            //파일 실제 이름
            String originalFilename = file.getOriginalFilename();

            //고유한 이름 만들기
            String uuid = UUID.randomUUID().toString();

            Path savePath = Paths.get(uploadPath, uuid + "_" + originalFilename);

            StudyroomImage studyroomImage = StudyroomImage.builder()
                    .studyroom(studyroom)
                    .path("http://localhost:8081/studycafeimg/"+uuid+"_"+originalFilename)
                    .seq(seq)
                    .build();
            seq++;

            studyService.saveroomImg(studyroomImage);

            try {
                file.transferTo(savePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return "redirect:/study/room_list?studycafeNo="+dto.getStudycafe().getStudycafeNo();
    }

    //스터디룸 수정 페이지 이동
    @GetMapping("/room_modify")
    public void room_modify(Model model, @RequestParam Long studyroomNo){
        model.addAttribute("oneRoom",studyService.oneRoom(studyroomNo));
        model.addAttribute("studyroomimg",studyService.oneRoomImg(studyroomNo));
    }

    //스터디룸 수정
    @PostMapping("/room_modify")
    public String room_modifyOk(StudyroomDTO dto){
        int seq = 1;
        Studycafe studycafe = studyService.oneCafe(dto.getStudycafe().getStudycafeNo());

        Studyroom studyroom = Studyroom.builder()
                .studyroomNo(dto.getStudyroomNo())
                .name(dto.getName())
                .price(dto.getPrice())
                .studycafe(studycafe)
                .build();

        StudyroomDetail studyroomDetail = StudyroomDetail.builder()
                .maxNum(dto.getMaxNum())
                .beam(dto.getBeam())
                .wboard(dto.getWboard())
                .socket(dto.getSocket())
                .studyroom(studyroom)
                .studyroomNo(studyroom.getStudyroomNo())
                .build();

        studyService.saveroom(studyroom);
        studyService.saveroomDetail(studyroomDetail);

        List<MultipartFile> files = dto.getPath(); //여러개

        //파일이 비어 있지 않을 때만 저장
        if(files.get(0).getSize() != 0) {

            List<StudyroomImage> studyroomImageList = studyService.oneRoomImg(dto.getStudyroomNo());

            for(int i=0; i<studyroomImageList.size(); i++) {
                String cafeimgpath = studyroomImageList.get(i).getPath();
                int imgindex = cafeimgpath.lastIndexOf("/");
                String imgname = cafeimgpath.substring(imgindex + 1);

                File file1 = new File(uploadPath + "\\" + imgname);

                if (file1.exists()) {
                    file1.delete();
                }
            }

            studyService.deleteImg(studyroom);

            for (MultipartFile file : files) {

                //파일 실제 이름
                String originalFilename = file.getOriginalFilename();

                //고유한 이름 만들기
                String uuid = UUID.randomUUID().toString();

                Path savePath = Paths.get(uploadPath, uuid + "_" + originalFilename);

                StudyroomImage studyroomImage = StudyroomImage.builder()
                        .studyroom(studyroom)
                        .path("http://localhost:8081/studycafeimg/" + uuid + "_" + originalFilename)
                        .seq(seq)
                        .build();
                seq++;

                studyService.saveroomImg(studyroomImage);

                try {
                    file.transferTo(savePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return "redirect:/study/room_list?studycafeNo="+dto.getStudycafe().getStudycafeNo();
    }

    //스터디룸 삭제
    @PostMapping("/room_delete")
    public String deleteRoom(@RequestParam Long studyroomNo, @RequestParam Long studycafeNo, Model model){

        List<Reservation> reservationList = studyService.reservCheck(studyroomNo);

        if(reservationList.isEmpty() == true) {

            studyService.deletereserv(studycafeNo);


            Studyroom studyroom = studyService.oneRoom(studyroomNo);

            List<StudyroomImage> studyroomImageList = studyService.oneRoomImg(studyroomNo);

            //디렉토리 안에 파일 삭제
            for(int i=0; i<studyroomImageList.size(); i++) {
                String cafeimgpath = studyroomImageList.get(i).getPath();
                int imgindex = cafeimgpath.lastIndexOf("/");
                String imgname = cafeimgpath.substring(imgindex + 1);

                File file1 = new File(uploadPath + "\\" + imgname);

                if (file1.exists()) {
                    file1.delete();
                }
            }
            studyService.deleteImg(studyroom);
            studyService.deleteRoom(studyroomNo);

        }else if (reservationList.isEmpty() == false){
            model.addAttribute("msg","남아있는 예약이 있습니다");
            model.addAttribute("url","/study/room_list?studycafeNo="+studycafeNo);
            return "/study/alert";
        }

        return "redirect:/study/room_list?studycafeNo="+studycafeNo;
    }
}
