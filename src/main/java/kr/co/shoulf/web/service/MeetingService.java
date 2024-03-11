package kr.co.shoulf.web.service;

import jakarta.transaction.Transactional;
import kr.co.shoulf.web.dto.MeetingDTO;
import kr.co.shoulf.web.dto.ReservPaymentDTO;
import kr.co.shoulf.web.entity.*;
import kr.co.shoulf.web.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingService {
    private static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
    private static final String admin_Key = "DEV7E26398A88DF32D8A3C6C7C9D84D93C607D91"; // 공개 조심! 본인 애플리케이션의 어드민 키를 넣어주세요
    private final MoimRepository moimRepository;
    private final MeetingRepository meetingRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final StudyroomRepository studyroomRepository;
    private final StudycafeRepository studycafeRepository;
    private KakaopayDTO kakaopayDTO;

    //모임번호로 미팅 리스트 불러오기
    public List<Meeting> meetingList(Long moimNo) {
        List<Meeting> meetingList = meetingRepository.findByMoim_MoimNo(moimNo);
        return meetingList;
    }

    //미팅 리스트 json 형식으로 보내기
    public List<Map<String, Object>> getEventList(Long moimNo) {
        List<Meeting> meetingList = meetingList(moimNo);

        List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        meetingList.forEach(meeting -> {
            Map<String, Object> event = new HashMap<String, Object>(); // 맵 객체를 반복문 내에서 생성
            String day = sdf.format(meeting.getMeetDate());
            event.put("title", meeting.getContents());
            event.put("start", day);
            event.put("end", day);
            event.put("description", meeting.getAddr());
            event.put("color", "#7B27FF");

            eventList.add(event);
        });

        return eventList;
    }

    //미팅 정보 추가
    public void writeMeeting(MeetingDTO meetingDTO) {
        String meetingDate = meetingDTO.getStartDate() + " " + meetingDTO.getStartTime();
        Optional<Moim> result = moimRepository.findById(meetingDTO.getMoimNo());
        Moim moim = result.orElseThrow();

        Meeting meeting = Meeting.builder()
                .contents(meetingDTO.getTitle())
                .addr(meetingDTO.getAddr())
                .meetDate(Timestamp.valueOf(meetingDate))
                .moim(moim)
                .build();

        meetingRepository.save(meeting);
    }

    //모임번호로 등록 회원 정보 가져오기
    public Users getUsers(Authentication authentication) {
//        Optional<Moim> result = moimRepository.findById(moimNo);
//        Moim moim = result.orElseThrow();
        Users users = (Users) authentication.getPrincipal();
        return users;
    }

    //일반 결제 미팅 예약 결제 등록
    public void writeReservPayment(ReservPaymentDTO reservPaymentDTO, Authentication authentication) {
//        Optional<Users> userss = userRepository.findById(reservPaymentDTO.getUserNo());
//        Users users = userss.orElseThrow();

        Users users = (Users) authentication.getPrincipal();

        String meetingDate = reservPaymentDTO.getStartDate() + " " + reservPaymentDTO.getStartTime();

        Optional<Moim> result = moimRepository.findById(reservPaymentDTO.getMoimNo());
        Moim moim = result.orElseThrow();

        Optional<Studyroom> studyrooms = studyroomRepository.findById(reservPaymentDTO.getStudyroomNo());
        Studyroom studyroom = studyrooms.orElseThrow();

        LocalDate now = LocalDate.now();

        Meeting meeting = Meeting.builder()
                .contents(reservPaymentDTO.getTitle())
                .addr(reservPaymentDTO.getAddr())
                .meetDate(Timestamp.valueOf(meetingDate))
                .moim(moim)
                .build();

        Reservation reservation = null;
        try {
            reservation = Reservation.builder()
                    .checkin(Date.valueOf(reservPaymentDTO.getCheckin()))
                    .ip(Inet4Address.getLocalHost().getHostAddress())
                    .users(users)
                    .meeting(meeting)
                    .studyroom(studyroom)
                    .build();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        Payment payment = Payment.builder()
                .approvalNum(reservPaymentDTO.getApprovalNum())
                .cardNum(reservPaymentDTO.getCardNum())
                .name(reservPaymentDTO.getName())
                .productName(reservPaymentDTO.getProductName())
                .email(reservPaymentDTO.getEmail())
                .price(reservPaymentDTO.getPrice())
                .payDate(Date.valueOf(now))
                .reservation(reservation)
                .build();

        meetingRepository.save(meeting);
        reservationRepository.save(reservation);
        paymentRepository.save(payment);
    }

    //스터디룸 이름 ajax로 보내기
    public Map<String, Object> getStudyroom(MeetingDTO meetingDTO) {
        String startDate = meetingDTO.getStartDate();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Date utilDate = null;
        try {
            utilDate = sdf.parse(startDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Timestamp timestamp = new java.sql.Timestamp(utilDate.getTime());

        Meeting meeting = meetingRepository.findByMeetDateAndMoim_MoimNo(timestamp, meetingDTO.getMoimNo());

        Map<String, Object> event = null;

        Reservation reservation = reservationRepository.findByMeeting_MeetingNo(meeting.getMeetingNo());

        if (reservation != null) {

            Studyroom studyroom = reservation.getStudyroom();

            Studycafe studycafe = studyroom.getStudycafe();

            event = new HashMap<String, Object>();
            event.put("studycafeName", studycafe.getName());
            event.put("studyroomName", studyroom.getName());

        }

        return event;
    }

    //미팅 정보 수정 추가
    public void modifyMeeting(MeetingDTO meetingDTO) {
        Optional<Moim> result = moimRepository.findById(meetingDTO.getMoimNo());
        Moim moim = result.orElseThrow();

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowtime = date.format(sdf);

        String meetingDate = meetingDTO.getStartDate()+" "+meetingDTO.getStartTime();

        Meeting meetings = meetingRepository.findByMeetDateAndMoim_MoimNo(Timestamp.valueOf(meetingDTO.getEndDate()), meetingDTO.getMoimNo());

        Meeting meeting = Meeting.builder()
                .meetingNo(meetings.getMeetingNo())
                .moim(moim)
                .contents(meetingDTO.getTitle())
                .addr(meetingDTO.getAddr())
                .meetDate(Timestamp.valueOf(meetingDate))
                .regdate(Timestamp.valueOf(nowtime))
                .build();

        meetingRepository.save(meeting);
    }
}
