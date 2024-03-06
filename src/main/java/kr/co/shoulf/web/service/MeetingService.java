package kr.co.shoulf.web.service;

import jakarta.transaction.Transactional;
import kr.co.shoulf.web.entity.Meeting;
import kr.co.shoulf.web.repository.MeetingRepository;
import kr.co.shoulf.web.repository.MoimRepository;
import kr.co.shoulf.web.repository.PaymentRepository;
import kr.co.shoulf.web.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingService {
    private final MoimRepository moimRepository;
    private final MeetingRepository meetingRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;

    //모임번호로 미팅 리스트 불러오기
    public List<Meeting> meetingList(Long moimNo) {
        List<Meeting> meetingList = meetingRepository.findByMoim_MoimNo(moimNo);
        return meetingList;
    }

    //미팅 리스트 json 형식으로 보내기
    public List<Map<String, Object>> getEventList(Long moimNo) {
        List<Meeting> meetingList = meetingList(moimNo);

        List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
}
