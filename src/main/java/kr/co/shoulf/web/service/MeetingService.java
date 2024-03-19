package kr.co.shoulf.web.service;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonObject;
import jakarta.transaction.Transactional;
import kr.co.shoulf.web.dto.MeetingDTO;
import kr.co.shoulf.web.dto.ReservPaymentDTO;
import kr.co.shoulf.web.entity.*;
import kr.co.shoulf.web.repository.*;
import kr.co.shoulf.web.security.custom.userDetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.Inet4Address;
import java.net.URL;
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
    private final MoimRepository moimRepository;
    private final MeetingRepository meetingRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final StudyroomRepository studyroomRepository;

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

    //일반 결제 미팅 예약 결제 등록
    public void writeReservPayment(ReservPaymentDTO reservPaymentDTO,@AuthenticationPrincipal CustomUserDetails user) {

        Users users = userRepository.findByEmail(user.getUsername());

        String meetingDate = reservPaymentDTO.getCalendar_start_date() + " " + reservPaymentDTO.getStart_time();

        Optional<Moim> result = moimRepository.findById(reservPaymentDTO.getMoimNo());
        Moim moim = result.orElseThrow();

        Optional<Studyroom> studyrooms = studyroomRepository.findById(reservPaymentDTO.getStudyroomNo());
        Studyroom studyroom = studyrooms.orElseThrow();

        LocalDate now = LocalDate.now();

        Meeting meeting = Meeting.builder()
                .contents(reservPaymentDTO.getCalendar_content())
                .addr(reservPaymentDTO.getCalendar_addr())
                .meetDate(Timestamp.valueOf(meetingDate))
                .moim(moim)
                .build();

        Reservation reservation = null;
        try {
            reservation = Reservation.builder()
                    .checkin(Date.valueOf(reservPaymentDTO.getCalendar_start_date()))
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
                .productName(reservPaymentDTO.getStudycafename())
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
        Timestamp timestamp = new Timestamp(utilDate.getTime());

        Meeting meeting = meetingRepository.findByMeetDateAndMoim_MoimNo(timestamp, meetingDTO.getMoimNo());

        Map<String, Object> event = null;

        Reservation reservation = reservationRepository.findByMeeting_MeetingNo(meeting.getMeetingNo());

        if (reservation != null) {

            if(reservation.getStatus()==1) {
                Studyroom studyroom = reservation.getStudyroom();

                Studycafe studycafe = studyroom.getStudycafe();

                event = new HashMap<String, Object>();
                event.put("studycafeName", studycafe.getName());
                event.put("studyroomName", studyroom.getName());
                event.put("studyroomNo", studyroom.getStudyroomNo());
            }
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

    //미팅 삭제
    public void deleteMeeting(Long moimNo, String meetingdate) {
        Meeting meetings = meetingRepository.findByMeetDateAndMoim_MoimNo(Timestamp.valueOf(meetingdate), moimNo);
        Reservation reservation = reservationRepository.findByMeeting_MeetingNo(meetings.getMeetingNo());
        if(reservation != null) {
            reservation.setMeeting(null);
            reservationRepository.save(reservation);
        }

        meetingRepository.delete(meetings);
    }

    //스터디룸 예약 취소
    public void cancelRoom(String endDate, Long roomNo2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        try {
            date = sdf.parse(endDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Reservation reservation = reservationRepository.findByCheckinAndStudyroom_StudyroomNo(date, roomNo2);
        Payment payment = paymentRepository.findByReservation_ReservationNo(reservation.getReservationNo());

        reservation.setStatus(2);
        payment.setStatus(2);

        reservationRepository.save(reservation);
        paymentRepository.save(payment);
    }

    //토큰 발급받기
    public String getToken(String apiKey, String secretKey) throws IOException {
        URL url = new URL("https://api.iamport.kr/users/getToken");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        // 요청 방식을 POST로 설정
        conn.setRequestMethod("POST");

        // 요청의 Content-Type과 Accept 헤더 설정
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");

        // 해당 연결을 출력 스트림(요청)으로 사용
        conn.setDoOutput(true);

        // JSON 객체에 해당 API가 필요로하는 데이터 추가.
        JsonObject json = new JsonObject();
        json.addProperty("imp_key", apiKey);
        json.addProperty("imp_secret", secretKey);

        // 출력 스트림으로 해당 conn에 요청
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(json.toString()); // json 객체를 문자열 형태로 HTTP 요청 본문에 추가
        bw.flush(); // BufferedWriter 비우기
        bw.close(); // BufferedWriter 종료

        // 입력 스트림으로 conn 요청에 대한 응답 반환
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        Gson gson = new Gson(); // 응답 데이터를 자바 객체로 변환
        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
        String accessToken = gson.fromJson(response, Map.class).get("access_token").toString();
        br.close(); // BufferedReader 종료

        conn.disconnect(); // 연결 종료

        return accessToken;
    }

    //결제 취소하기
    public void refundRequest(String access_token, String merchant_uid) throws IOException {
        URL url = new URL("https://api.iamport.kr/payments/cancel");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("POST");

        // 요청의 Content-Type, Accept, Authorization 헤더 설정
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", access_token);

        // 해당 연결을 출력 스트림(요청)으로 사용
        conn.setDoOutput(true);

        // JSON 객체에 해당 API가 필요로하는 데이터 추가.
        JsonObject json = new JsonObject();
        json.addProperty("merchant_uid", merchant_uid);

        // 출력 스트림으로 해당 conn에 요청
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(json.toString());
        bw.flush();
        bw.close();

        // 입력 스트림으로 conn 요청에 대한 응답 반환
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        br.close();
        conn.disconnect();
    }

}
