//package kr.co.shoulf.web.repository;
//
//
//import kr.co.shoulf.web.entity.*;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Slf4j
//@SpringBootTest
//@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
//public class TestDataInsert {
//    @Autowired
//    private PositionRepository positionRepository;
//    @Autowired
//    private PositionDetailRepository positionDetailRepository;
//    @Autowired
//    private OnlineRepository onlineRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserDetailRepository userDetailRepository;
//    @Autowired
//    private UserJobRepository userJobRepository;
//    @Autowired
//    private UserPortfolioRepository userPortfolioRepository;
//    @Autowired
//    private UserLanguageRepository userLanguageRepository;
//    @Autowired
//    private NoticeRepository noticeRepository;
//    @Autowired
//    private MessageRepository messageRepository;
//    @Autowired
//    private BoardRepository boardRepository;
//    @Autowired
//    private ReplyRepository replyRepository;
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @Autowired
//    private StudyCategoryRepository studyCategoryRepository;
//    @Autowired
//    private PlatformRepository platformRepository;
//    @Autowired
//    private UserInterestCategoryRepository userInterestCategoryRepository;
//    @Autowired
//    private UserOnlineRepository userOnlineRepository;
//    @Autowired
//    private MoimRepository moimRepository;
//    @Autowired
//    private MoimDetailRepository moimDetailRepository;
//    @Autowired
//    private MoimLikeRepository moimLikeRepository;
//    @Autowired
//    private ChecklistRepository checklistRepository;
//    @Autowired
//    private MoimProjectLinkRepository moimProjectLinkRepository;
//    @Autowired
//    private MoimLanguageRepository moimLanguageRepository;
//    @Autowired
//    private MoimProjectPlatformRepository moimProjectPlatformRepository;
//    @Autowired
//    private MeetingRepository meetingRepository;
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private MemberAnnualRepository memberAnnualRepository;
//    @Autowired
//    private MemberAnnualDetailRepository memberAnnualDetailRepository;
//    @Autowired
//    private MemberAnnualRejectedRepository memberAnnualRejectedRepository;
//    @Autowired
//    private MemberCommuteRepository memberCommuteRepository;
//    @Autowired
//    private MemberSalaryRepository memberSalaryRepository;
//    @Autowired
//    private MemberSalaryDetailRepository memberSalaryDetailRepository;
//    @Autowired
//    private StudycafeRepository studycafeRepository;
//    @Autowired
//    private StudyroomRepository studyroomRepository;
//    @Autowired
//    private StudyroomDetailRepository studyroomDetailRepository;
//    @Autowired
//    private StudyroomImageRepository studyroomImageRepository;
//    @Autowired
//    private ReservationRepository reservationRepository;
//    @Autowired
//    private PaymentRepository paymentRepository;
//    @Autowired
//    private MoimHeadcountRepository moimHeadcountRepository;
//    @Autowired
//    private MoimParticipantsRepository moimParticipantsRepository;
//    @Autowired
//    private MoimParticipantsRejectRepository moimParticipantsRejectRepository;
//
//    @Test
//    @DisplayName("직무 대분류 추가")
//    @Order(1)
//    void insertPosition() {
//        positionRepository.save(Position.builder().bigName("기획").build());
//        positionRepository.save(Position.builder().bigName("디자인").build());
//        positionRepository.save(Position.builder().bigName("프론트엔드개발").build());
//        positionRepository.save(Position.builder().bigName("백엔드개발").build());
//        positionRepository.save(Position.builder().bigName("사업").build());
//        positionRepository.save(Position.builder().bigName("기타").build());
//    }
//
//    @Test
//    @DisplayName("직무 중분류 추가")
//    @Order(2)
//    void insertPositionDetail() {
//        List<Position> positionList = positionRepository.findAll();
//        List<List<String>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList("UI/UX 기획","게임기획", "프로젝트 매니저", "프로덕트 매니저/오너", "컨텐트 기획", "하드웨어(제품) 기획", "기타"));
//        dataList.add(Arrays.asList("그래픽디자인", "UI/UX디자인", "3D 디자인", "도트 디자인", "모션/이펙트 디자인", "BX/브랜딩 디자인", "기타"));
//        dataList.add(Arrays.asList("IOS", "안드로이드", "웹프론트엔드", "웹퍼블리셔", "크로스플랫폼", "게임클라이언트", "설치형 소프트웨어(비게임)", "인베디드 SW"));
//        dataList.add(Arrays.asList("웹서버", "블록체인", "AI", "DB/빅데이터/DS", "게임 서버", "프롬프트 엔지니어", "아키텍트(TA/DA/AA)", "보안/화이트 해커"));
//        dataList.add(Arrays.asList("사업기획 (BD/BA)", "마케팅", "재무/회계", "영업", "전략/컨설팅", "투자/고문", "인사(HR)", "기타"));
//        dataList.add(Arrays.asList("작가/블로거", "인플루언서/스트리머", "법률/노무", "의료/의학", "요식업/쉐프", "프로듀서/CP", "작곡(사운드)", "영상", "운영", "QA", "기타"));
//
//        positionList.forEach(position -> {
//            Integer positionNo = position.getPositionNo() - 1;
//
//            dataList.get(positionNo).forEach(data -> {
//                positionDetailRepository.save(PositionDetail.builder().position(position).middleName(data).build());
//            });
//        });
//    }
//
//    @Test
//    @DisplayName("온/오프라인 추가")
//    @Order(3)
//    void insertOnline() {
//        List<List<String>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList("온라인/오프라인 모두 가능"));
//        dataList.add(Arrays.asList("온라인만 가능"));
//        dataList.add(Arrays.asList("오프라인만 가능"));
//
//        dataList.forEach(data -> {
//            onlineRepository.save(
//                    Online.builder()
//                            .onlineName(data.get(0))
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("분야 추가")
//    @Order(4)
//    void insertCategory() {
//        List<List<String>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList("O2O"));
//        dataList.add(Arrays.asList("공유서비스"));
//        dataList.add(Arrays.asList("데이트/결혼"));
//        dataList.add(Arrays.asList("여행"));
//        dataList.add(Arrays.asList("소셜네트워크"));
//        dataList.add(Arrays.asList("뷰티/패션"));
//        dataList.add(Arrays.asList("이커머스"));
//        dataList.add(Arrays.asList("엔터테인먼트"));
//        dataList.add(Arrays.asList("게임"));
//        dataList.add(Arrays.asList("헬스/스포츠"));
//        dataList.add(Arrays.asList("뉴스/정보"));
//        dataList.add(Arrays.asList("유틸"));
//        dataList.add(Arrays.asList("금융"));
//        dataList.add(Arrays.asList("부동산/인테리어"));
//        dataList.add(Arrays.asList("종교"));
//        dataList.add(Arrays.asList("교육"));
//        dataList.add(Arrays.asList("의료/병원"));
//        dataList.add(Arrays.asList("모빌리티(교통)"));
//        dataList.add(Arrays.asList("육아/출산"));
//        dataList.add(Arrays.asList("우주/외계인"));
//
//        dataList.forEach(data -> {
//            categoryRepository.save(
//                    Category.builder()
//                            .categoryName(data.get(0))
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("출시 플랫폼 추가")
//    @Order(5)
//    void insertPlatform() {
//        List<List<String>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList("미정(논의 후 확정)"));
//        dataList.add(Arrays.asList("반응형 웹(PC/모바일)"));
//        dataList.add(Arrays.asList("안드로이드 앱"));
//        dataList.add(Arrays.asList("IOS 앱"));
//        dataList.add(Arrays.asList("기타"));
//
//        dataList.forEach(data -> {
//            platformRepository.save(
//                    Platform.builder()
//                            .name(data.get(0))
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("스터디 관심분야 추가")
//    @Order(6)
//    void insertStudyCategory() {
//        List<List<String>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList("기획&PO"));
//        dataList.add(Arrays.asList("디자인&UX"));
//        dataList.add(Arrays.asList("프론트엔드개발"));
//        dataList.add(Arrays.asList("벡엔드 개발"));
//        dataList.add(Arrays.asList("사업&리서치"));
//        dataList.add(Arrays.asList("어학&학업"));
//        dataList.add(Arrays.asList("취업&취직"));
//        dataList.add(Arrays.asList("시험&고시"));
//        dataList.add(Arrays.asList("예술&공예"));
//        dataList.add(Arrays.asList("요리&식음관련"));
//        dataList.add(Arrays.asList("운동&헬스"));
//        dataList.add(Arrays.asList("사진&영상"));
//        dataList.add(Arrays.asList("기타"));
//
//        dataList.forEach(data -> {
//            studyCategoryRepository.save(
//                    StudyCategory.builder()
//                            .studyCategoryName(data.get(0))
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("회원 정보 추가 (회원, 회원상세)")
//    @Order(7)
//    void insertUserData() {
//        List<Users> userList = new ArrayList<>();
//        List<UserDetail> userDetailList = new ArrayList<>();
//
//        // 회원 정보
//        List<List<String>> userDataList = new ArrayList<>();
//        userDataList.add(Arrays.asList("aaa@naver.com", "bbb@naver.com", "ccc@naver.com", "ddd@naver.com", "eee@naver.com", "fff@naver.com", "ggg@naver.com", "hhh@naver.com", "iii@naver.com", "jjj@naver.com"));
//        userDataList.add(Arrays.asList("1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999", "1010"));
//        userDataList.add(Arrays.asList("홍길동", "김광현", "안정환", "손흥민", "이영표", "박지성", "조규성", "이강인", "황인범", "김민재"));
//
//        for(int i=0; i<userDataList.get(0).size(); i++) {
//            userList.add(Users.builder().email(userDataList.get(0).get(i)).pass(userDataList.get(1).get(i)).nickname(userDataList.get(2).get(i)).build());
//        }
//
//        // 회원 상세 정보
//        List<List<String>> detailDataList = new ArrayList<>();
//        detailDataList.add(Arrays.asList(
//                "안녕하세요 커리어 관리 플랫폼 기획 중인 홍길동 입니다.",
//                "안녕하세요 현재 7년차 개발자입니다.",
//                "데이터와 소통을 통해 문제해결을 즐기는 개발자 안정환입니다.",
//                "다양한 협업 툴로 협업을 즐기는 소통형 개발자입니다.",
//                "사이드프로젝트를 통해 여러가지를 배우고 있습니다.",
//                "신입 웹 개발자를 희망하는 박지성입니다.",
//                "공감을 이끄는 디자이너 조규성입니다",
//                "자바개발자입니다. 잘부탁드립니다",
//                "안녕하세요 성장 진행형 개발자입니다.",
//                "안녕하세요 3년차 백엔드 개발자입니다."
//        ));
//        detailDataList.add(Arrays.asList("서울특별시", "서울특별시", "경기도", "상관없음", "상관없음", "서울특별시", "대구광역시", "경기도", "상관없음", "상관없음"));
//        detailDataList.add(Arrays.asList(
//                "https://letspl.me/assets/images/prof-no-img.png",
//                "https://letspl.me/assets/images/prof-no-img.png",
//                "https://letspl.me/assets/images/prof-no-img.png",
//                "https://letspl.me/assets/images/prof-no-img.png",
//                "https://letspl.me/assets/images/prof-no-img.png",
//                "https://letspl.me/assets/images/prof-no-img.png",
//                "https://letspl.me/assets/images/prof-no-img.png",
//                "https://letspl.me/assets/images/prof-no-img.png",
//                "https://letspl.me/assets/images/prof-no-img.png",
//                "https://letspl.me/assets/images/prof-no-img.png"
//        ));
//        detailDataList.add(Arrays.asList(
//                "https://github.com/aaa",
//                "https://github.com/bbb",
//                "https://github.com/ccc",
//                "https://github.com/ddd",
//                "https://github.com/eee",
//                "https://github.com/fff",
//                "https://github.com/ggg",
//                "https://github.com/hhh",
//                "https://github.com/iii",
//                "https://github.com/jjj"
//        ));
//        detailDataList.add(Arrays.asList(
//                "https://blog.naver.com/aaa",
//                "https://blog.naver.com/bbb",
//                "https://blog.naver.com/ccc",
//                "https://blog.naver.com/ddd",
//                "https://blog.naver.com/eee",
//                "https://blog.naver.com/fff",
//                "https://blog.naver.com/ggg",
//                "https://blog.naver.com/hhh",
//                "https://blog.naver.com/iii",
//                "https://blog.naver.com/jjj"
//        ));
//
//        for(int i=0; i<detailDataList.get(0).size(); i++) {
//            userDetailList.add(
//                    UserDetail.builder()
//                            .users(userList.get(i))
//                            .introduce(detailDataList.get(0).get(i))
//                            .preferArea(detailDataList.get(1).get(i))
//                            .profileImg(detailDataList.get(2).get(i))
//                            .gitLink(detailDataList.get(3).get(i))
//                            .blogLink(detailDataList.get(4).get(i))
//                            .build()
//            );
//        }
//
//        // 데이터 추가
//        for(int i=0; i<userDetailList.size(); i++) {
//            userDetailRepository.save(userDetailList.get(i));
//            //userRepository.save(userList.get(i));
//        }
//    }
//
//    @Test
//    @DisplayName("회원 직무 추가")
//    @Order(8)
//    void insertUserJob() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 직무능력치, 경력, 직무중분류번호, 회원번호
//        dataList.add(Arrays.asList(5, 10, 3, 1L));
//        dataList.add(Arrays.asList(4, 7, 25, 2L));
//        dataList.add(Arrays.asList(1, 0, 1, 3L));
//        dataList.add(Arrays.asList(3, 5, 15, 4L));
//        dataList.add(Arrays.asList(3, 3, 8, 5L));
//        dataList.add(Arrays.asList(1, 1, 23, 6L));
//        dataList.add(Arrays.asList(5, 8, 9, 7L));
//        dataList.add(Arrays.asList(4, 5, 23, 8L));
//        dataList.add(Arrays.asList(1, 0, 17, 9L));
//        dataList.add(Arrays.asList(3, 3, 23, 10L));
//
//        dataList.forEach(data -> {
//            Users users = userRepository.findById((Long) data.get(3)).get();
//            PositionDetail positionDetail = positionDetailRepository.findById((Integer) data.get(2)).get();
//
//            userJobRepository.save(
//                    UserJob.builder()
//                            .users(users)
//                            .positionDetail(positionDetail)
//                            .level((Integer) data.get(0))
//                            .career((Integer) data.get(1))
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("회원 포트폴리오 추가")
//    @Order(9)
//    void insertUserPortfolio() {
//        List<Long> userNoList = new ArrayList<>(Arrays.asList(1L, 4L, 7L));
//        List<String> urlList = new ArrayList<>(Arrays.asList(
//                "https://drive.google.com/aaa",
//                "https://drive.google.com/ddd",
//                "https://drive.google.com/ggg"
//        ));
//
//        for(int i=0; i<urlList.size(); i++) {
//            Users users = userRepository.findById(userNoList.get(i)).get();
//            userPortfolioRepository.save(
//                    UserPortfolio.builder()
//                            .url(urlList.get(i))
//                            .users(users)
//                            .build()
//            );
//        }
//    }
//
//    @Test
//    @DisplayName("회원 선호언어/기술 추가")
//    @Order(10)
//    void insertUserLanguage() {
//        List<Long> userNoList = new ArrayList<>(Arrays.asList(1L, 1L, 2L, 3L, 4L, 5L, 5L));
//        List<String> nameList = new ArrayList<>(Arrays.asList("java", "nodejs", "java", "nuxt3", "springboot", "javascript", "typescript"));
//
//        for(int i=0; i<nameList.size(); i++) {
//            Users users = userRepository.findById(userNoList.get(i)).get();
//            userLanguageRepository.save(
//                    UserLanguage.builder()
//                            .name(nameList.get(i))
//                            .users(users)
//                            .build()
//            );
//        }
//    }
//
//    @Test
//    @DisplayName("회원 관심분야 추가")
//    @Order(11)
//    void insertUserInter() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 회원번호, 관심분야 번호
//        dataList.add(Arrays.asList(1L, 1L));
//        dataList.add(Arrays.asList(1L, 2L));
//        dataList.add(Arrays.asList(1L, 11L));
//        dataList.add(Arrays.asList(1L, 13L));
//        dataList.add(Arrays.asList(1L, 16L));
//        dataList.add(Arrays.asList(2L, 5L));
//        dataList.add(Arrays.asList(2L, 8L));
//        dataList.add(Arrays.asList(2L, 10L));
//        dataList.add(Arrays.asList(2L, 14L));
//        dataList.add(Arrays.asList(2L, 18L));
//        dataList.add(Arrays.asList(3L, 2L));
//        dataList.add(Arrays.asList(3L, 6L));
//        dataList.add(Arrays.asList(3L, 7L));
//        dataList.add(Arrays.asList(3L, 8L));
//        dataList.add(Arrays.asList(3L, 20L));
//
//        dataList.forEach(data -> {
//            Users users = userRepository.findById((Long) data.get(0)).get();
//            Category category = categoryRepository.findById((Long) data.get(1)).get();
//
//            userInterestCategoryRepository.save(
//                    UserInterestCategory.builder()
//                            .category(category)
//                            .users(users)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("회원 온/오프라인 추가")
//    @Order(12)
//    void insertUserOnline() {
//        List<List<Object>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList(1L, 1L));
//        dataList.add(Arrays.asList(2L, 1L));
//        dataList.add(Arrays.asList(3L, 1L));
//        dataList.add(Arrays.asList(4L, 1L));
//        dataList.add(Arrays.asList(5L, 1L));
//        dataList.add(Arrays.asList(6L, 1L));
//        dataList.add(Arrays.asList(7L, 2L));
//        dataList.add(Arrays.asList(8L, 3L));
//        dataList.add(Arrays.asList(9L, 1L));
//        dataList.add(Arrays.asList(10L, 1L));
//
//        dataList.forEach(data -> {
//            Users users = userRepository.findById((Long) data.get(0)).get();
//            Online online = onlineRepository.findById((Long) data.get(1)).get();
//
//            userOnlineRepository.save(
//                    UserOnline.builder()
//                            .users(users)
//                            .online(online)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("알림 추가")
//    @Order(13)
//    void insertNotice() {
//        List<List<Object>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList("새로운 메세지가 도착했습니다.", 2L));
//        dataList.add(Arrays.asList("새로운 메세지가 도착했습니다.", 1L));
//        dataList.add(Arrays.asList("새로운 메세지가 도착했습니다.", 10L));
//        dataList.add(Arrays.asList("xxx 프로젝트의 백엔드개발 지원자가 있습니다.", 4L));
//
//        dataList.forEach(data -> {
//            Users users = userRepository.findById((Long) data.get(1)).get();
//
//            noticeRepository.save(
//                    Notice.builder()
//                            .user(users)
//                            .contents(data.get(0).toString())
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("메세지 추가")
//    @Order(14)
//    void insertMessage() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 그룹번호, 내용, 확인상태, 아이피, 발신자, 수신자
//        dataList.add(Arrays.asList(1L, "안녕하세요", 2, "111.111.1.1", 1L, 10L));
//        dataList.add(Arrays.asList(1L, "반가워요", 2, "101.010.1.0", 10L, 1L));
//        dataList.add(Arrays.asList(1L, "하하하", 1, "111.111.1.1", 1L, 10L));
//        dataList.add(Arrays.asList(2L, "안녕하세요", 2, "222.222.2.2", 2L, 9L));
//        dataList.add(Arrays.asList(2L, "네", 1, "999.999.9.9", 9L, 2L));
//        dataList.add(Arrays.asList(3L, "안녕하세요", 2, "333.333.3.3", 3L, 8L));
//        dataList.add(Arrays.asList(3L, "?", 2, "888.888.8.8", 8L, 3L));
//        dataList.add(Arrays.asList(3L, "질문이 있어요", 1, "333.333.3.3", 3L, 8L));
//        dataList.add(Arrays.asList(4L, "안녕하세요", 1, "444.444.4.4", 4L, 7L));
//
//        dataList.forEach(data -> {
//            Users sender = userRepository.findById((Long) data.get(4)).get();
//            Users receiver = userRepository.findById((Long) data.get(5)).get();
//
//            messageRepository.save(
//                    Message.builder()
//                            .groupNo((Long) data.get(0))
//                            .contents(data.get(1).toString())
//                            .status((Integer) data.get(2))
//                            .ip(data.get(3).toString())
//                            .receiver(receiver)
//                            .sender(sender)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("게시판 추가")
//    @Order(15)
//    void insertBoard() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 카테고리, 제목, 내용, 아이피, 작성자번호
//        dataList.add(Arrays.asList(1, "프로젝트를 하고싶은데", "프로젝트를 진행하고 싶은데요", "111.111.1.1", 1L));
//        dataList.add(Arrays.asList(1, "잠시 홍보하겠습니다! 그냥 지나가주세요ㅎ", "이직 준비하려면 이거 무조건 사두세요. ", "222.222.2.2", 2L));
//        dataList.add(Arrays.asList(2, "백엔드 입문 관련되어서 질문 드립니다!", "백엔드 개발자 입문으로 추천 하시는 프레임워크 있으실까요?", "333.333.3.3", 3L));
//        dataList.add(Arrays.asList(1, "진로고민", "제가 현재는 일과 병행을 하여 개발자를 준비중인데요.하루에 3~4시간 많으면 5시간 정도를 공부 할 환경이 나오는데", "333.333.3.3", 3L));
//        dataList.add(Arrays.asList(2, "타임레터혹시 사라진건가요?", "접속이 안되네요ㅜㅜ", "444.444.4.4", 4L));
//        dataList.add(Arrays.asList(2, "React json load error", "리액트 app.js에서 json을 불러 출력하려고 하면 아래와 같은 에러가 나와요", "555.555.5.5", 5L));
//        dataList.add(Arrays.asList(1, "프론 공부 8개월째", "프론 공부 8개월째인데 어렵네요", "666.666.6.6", 6L));
//        dataList.add(Arrays.asList(2, "JPA로 대댓글 기능 구현할 때 어떤 방식으로 구현하시나요??", "다들 어떤 방식으로 구현하시는지 궁금합니다", "777.777.7.7", 7L));
//
//        dataList.forEach(data -> {
//            Users users = userRepository.findById((Long) data.get(4)).get();
//
//            boardRepository.save(
//                    Board.builder()
//                            .cate((Integer) data.get(0))
//                            .title(data.get(1).toString())
//                            .contents(data.get(2).toString())
//                            .ip(data.get(3).toString())
//                            .users(users)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("댓글 추가")
//    @Order(16)
//    void insertReply() {
//        List<List<Object>> dataList = new ArrayList<>();
//
//        dataList.add(Arrays.asList("어떤 프로젝트를 하고 시프세요?", 1, null, 1L, 2L));
//        dataList.add(Arrays.asList("저도 프로젝트 해보고 싶습니다.", 1, null, 1L, 6L));
//        dataList.add(Arrays.asList("애견관련 프로젝트를 하고 싶어요", 2, 1L, 1L, 1L));
//        dataList.add(Arrays.asList("자바를 사용하신다면 스프링 공부해보세요", 1, null, 3L, 9L));
//        dataList.add(Arrays.asList("접속 잘 되는데요?", 1, null, 5L, 2L));
//        dataList.add(Arrays.asList("저는 안되는데..", 2, 5L, 5L, 4L));
//
//        dataList.forEach(data -> {
//            Reply parent;
//            Board board = boardRepository.findById((Long) data.get(3)).orElse(null);
//            Users users = userRepository.findById((Long) data.get(4)).orElse(null);
//
//            if( data.get(2)!=null ) parent = replyRepository.findById((Long) data.get(2)).orElse(null);
//            else                    parent = null;
//
//            replyRepository.save(
//                    Reply.builder()
//                            .contents(data.get(0).toString())
//                            .depth((Integer) data.get(1))
//                            .parentReply(parent)
//                            .board(board)
//                            .users(users)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("모임 추가")
//    @Order(17)
//    void insertMoim() {
//        List<List<Object>> moimDataList = new ArrayList<>();
//        // 타입	제목, 요약설명, 회원번호
//        moimDataList.add(Arrays.asList("project", "프로젝트1", "애견앱 개발", 1L));
//        moimDataList.add(Arrays.asList("project", "프로젝트2", "데이팅앱 개발", 2L));
//        moimDataList.add(Arrays.asList("study", "스터디1", "스프링스터디", 3L));
//        moimDataList.add(Arrays.asList("study", "스터디2", "파이썬스터디", 4L));
//        moimDataList.add(Arrays.asList("project", "프로젝트3", "쇼핑몰 개발", 5L));
//        moimDataList.add(Arrays.asList("project", "프로젝트4", "헬스앱 개발", 6L));
//        moimDataList.add(Arrays.asList("study", "스터디3", "코테 스터디", 7L));
//        moimDataList.add(Arrays.asList("study", "스터디4", "SQL 스터디", 8L));
//        moimDataList.add(Arrays.asList("project", "프로젝트5", "인공지능 활용앱 개발", 9L));
//        moimDataList.add(Arrays.asList("project", "프로젝트6", "소셜앱 개발", 10L));
//
//        List<List<Object>> moimDetailDataList = new ArrayList<>();
//        //상세설명	썸네일 이미지	참가비	오프라인 장소	온오프라인 번호	관심분야 번호	스터디분야 번호
//        moimDetailDataList.add(Arrays.asList("애견~~~~~~~~", "https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png", 20000, "서울", 1L, 11L, null));
//        moimDetailDataList.add(Arrays.asList("데이팅~~~~~~~~~", "https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png", 20000, "서울", 1L, 3L, null));
//        moimDetailDataList.add(Arrays.asList("스프링~~~~~~~~~", "https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png", 10000, "경기", 1L, null, 1L));
//        moimDetailDataList.add(Arrays.asList("파이썬~~~~~~~~~~~", "https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png", 10000, "서울", 1L, null, 2L));
//        moimDetailDataList.add(Arrays.asList("옷 쇼핑몰~~~~~~~~", "https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png", 20000, "부산", 1L, 6L, null));
//        moimDetailDataList.add(Arrays.asList("헬스앱~~~~~~~~~~", "https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png", 20000, "서울", 1L, 10L, null));
//        moimDetailDataList.add(Arrays.asList("코테~~~~~~~~~~~", "https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png", 10000, "경기", 2L, null, 3L));
//        moimDetailDataList.add(Arrays.asList("SQL~~~~~~~~~~~", "https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png", 10000, null, 3L, null, 1L));
//        moimDetailDataList.add(Arrays.asList("인공지능~~~~~~~~~", "https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png", 20000, "대구", 1L, 12L, null));
//        moimDetailDataList.add(Arrays.asList("SNS~~~~~~~~~~~~~", "https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png", 20000, "강릉", 1L, 5L, null));
//
//        AtomicInteger index = new AtomicInteger();
//        moimDataList.forEach(moimData -> {
//            int dex = index.getAndIncrement();
//            Users users = userRepository.findById((Long) moimData.get(3)).get();
//
//            Moim moim = Moim.builder()
//                    .type(moimData.get(0).toString())
//                    .subject(moimData.get(1).toString())
//                    .shortDesc(moimData.get(2).toString())
//                    .users(users)
//                    .build();
//
//            List<Object> moimDetailData = moimDetailDataList.get(dex);
//            Category category;
//            StudyCategory studyCategory;
//            Online online = onlineRepository.findById((Long) moimDetailData.get(4)).orElse(null);
//            if( moimDetailData.get(5)!=null )   category = categoryRepository.findById((Long) moimDetailData.get(5)).orElse(null);
//            else                                category = null;
//            if( moimDetailData.get(6)!=null )   studyCategory = studyCategoryRepository.findById((Long) moimDetailData.get(6)).orElse(null);
//            else                                studyCategory = null;
//
//            MoimDetail moimDetail = MoimDetail.builder()
//                    .detailDesc(moimDetailData.get(0).toString())
//                    .moimImg(moimDetailData.get(1).toString())
//                    .fee((Integer) moimDetailData.get(2))
//                    .offAddr((String) moimDetailData.get(3))
//                    .online(online)
//                    .moim(moim)
//                    .category(category)
//                    .studyCategory(studyCategory)
//                    .build();
//
//            moimDetailRepository.save(moimDetail);
//        });
//    }
//
//    @Test
//    @DisplayName("모임 인원 추가")
//    @Order(18)
//    void insertMoimHeadcount() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 모임번호, 인원, 직무중분류번호
//        dataList.add(Arrays.asList(1L, 1, 17));
//        dataList.add(Arrays.asList(1L, 1, 23));
//        dataList.add(Arrays.asList(2L, 2, 19));
//        dataList.add(Arrays.asList(2L, 3, 23));
//        dataList.add(Arrays.asList(2L, 1, 9));
//        dataList.add(Arrays.asList(3L, 8, null));
//        dataList.add(Arrays.asList(4L, 8, null));
//        dataList.add(Arrays.asList(5L, 1, 9));
//        dataList.add(Arrays.asList(5L, 2, 23));
//        dataList.add(Arrays.asList(5L, 1, 3));
//        dataList.add(Arrays.asList(6L, 2, 23));
//        dataList.add(Arrays.asList(7L, 8, null));
//        dataList.add(Arrays.asList(8L, 8, null));
//        dataList.add(Arrays.asList(9L, 1, 32));
//        dataList.add(Arrays.asList(9L, 1, 46));
//        dataList.add(Arrays.asList(9L, 1, 9));
//        dataList.add(Arrays.asList(10L, 2, 1));
//        dataList.add(Arrays.asList(10L, 3, 19));
//        dataList.add(Arrays.asList(10L, 2, 8));
//        dataList.add(Arrays.asList(10L, 2, 23));
//
//        dataList.forEach(data -> {
//            PositionDetail positionDetail;
//            Moim moim = moimRepository.findById((Long) data.get(0)).orElse(null);
//
//            if( data.get(2)!=null ) positionDetail = positionDetailRepository.findById((Integer) data.get(2)).orElse(null);
//            else                    positionDetail = null;
//
//            moimHeadcountRepository.save(
//                    MoimHeadcount.builder()
//                            .personnel((Integer) data.get(1))
//                            .moim(moim)
//                            .positionDetail(positionDetail)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("모임 지원자 추가")
//    @Order(19)
//    void insertMoimParticipants() {
//        List<List<Object>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList("소셜앱개발하고싶어서", "Y", 3, 1L, 17L));
//        dataList.add(Arrays.asList("인공지능 앱 개발해보고싶어서", "Y", 1, 2L, 14L));
//        dataList.add(Arrays.asList("SQL에 대해 더 배우고싶어서", "N", 1, 3L, 13L));
//        dataList.add(Arrays.asList("코테 준비하고싶어서", "N", 1, 4L, 12L));
//        dataList.add(Arrays.asList("헬스앱 개발해보고싶어서", "Y", 1, 5L, 11L));
//        dataList.add(Arrays.asList("쇼핑몰 개발해보고 싶어서", "Y", 1, 6L, 8L));
//        dataList.add(Arrays.asList("파이썬 배우고 싶어서", "N", 1, 7L, 7L));
//        dataList.add(Arrays.asList("스프링 더 배우고 싶어서", "N", 1, 8L, 6L));
//        dataList.add(Arrays.asList("데이팅 앱을 개발해보고 싶어서", "Y", 1, 9L, 3L));
//        dataList.add(Arrays.asList("애견앱 개발해보고싶어서", "Y", 2, 10L, 2L));
//        dataList.add(Arrays.asList("애견앱 개발해보고싶어서", "N", 2, 9L, 1L));
//
//        dataList.forEach(data -> {
//            Users users = userRepository.findById((Long) data.get(3)).orElse(null);
//            MoimHeadcount moimHeadcount = moimHeadcountRepository.findById((Long) data.get(4)).orElse(null);
//
//            moimParticipantsRepository.save(
//                    MoimParticipants.builder()
//                            .reason(data.get(0).toString())
//                            .job(data.get(1).toString())
//                            .status((Integer) data.get(2))
//                            .users(users)
//                            .moimHeadcount(moimHeadcount)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("회원 관심모임 추가")
//    @Order(20)
//    void insertMoimLike() {
//        List<List<Long>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList(1L, 10L));
//        dataList.add(Arrays.asList(2L, 9L));
//        dataList.add(Arrays.asList(3L, 8L));
//        dataList.add(Arrays.asList(4L, 7L));
//        dataList.add(Arrays.asList(5L, 6L));
//        dataList.add(Arrays.asList(6L, 5L));
//        dataList.add(Arrays.asList(7L, 4L));
//        dataList.add(Arrays.asList(8L, 3L));
//        dataList.add(Arrays.asList(9L, 2L));
//        dataList.add(Arrays.asList(10L, 1L));
//
//        dataList.forEach(data -> {
//            Users users = userRepository.findById(data.get(0)).orElse(null);
//            Moim moim = moimRepository.findById(data.get(1)).orElse(null);
//
//            moimLikeRepository.save(
//                    MoimLike.builder()
//                            .users(users)
//                            .moim(moim)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("체크리스트 추가")
//    @Order(21)
//    void insertCheckList() {
//        List<List<Object>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList("와이어프레임", 2, 1L, 1L));
//        dataList.add(Arrays.asList("데이터베이스 설계", 1, 1L, 1L));
//        dataList.add(Arrays.asList("웹서버 구축", 1, 1L, 9L));
//
//        dataList.forEach(data -> {
//            Users users = userRepository.findById((Long) data.get(3)).orElse(null);
//            Moim moim = moimRepository.findById((Long) data.get(2)).orElse(null);
//
//            checklistRepository.save(
//                    Checklist.builder()
//                            .contents(data.get(0).toString())
//                            .status((Integer) data.get(1))
//                            .users(users)
//                            .moim(moim)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("프로젝트 참고 링크 추가")
//    @Order(22)
//    void insertProjectLink() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 링크, 모임번호
//        dataList.add(Arrays.asList("https://github.com/bbb", 2L));
//        dataList.add(Arrays.asList("https://github.com/fff", 6L));
//        dataList.add(Arrays.asList("https://github.com/iii", 9L));
//
//        dataList.forEach(data -> {
//            Moim moim = moimRepository.findById((Long) data.get(1)).orElse(null);
//
//            moimProjectLinkRepository.save(
//                    MoimProjectLink.builder()
//                            .url(data.get(0).toString())
//                            .moim(moim)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("모임 사용기술/언어 추가")
//    @Order(23)
//    void insertMoimLanguage() {
//        List<List<Object>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList("java", 1L));
//        dataList.add(Arrays.asList("spring", 1L));
//        dataList.add(Arrays.asList("javascript", 1L));
//        dataList.add(Arrays.asList("kotlin", 2L));
//        dataList.add(Arrays.asList("php", 3L));
//        dataList.add(Arrays.asList("javascript", 3L));
//        dataList.add(Arrays.asList("html", 3L));
//        dataList.add(Arrays.asList("css", 3L));
//        dataList.add(Arrays.asList("java", 4L));
//        dataList.add(Arrays.asList("spring", 4L));
//        dataList.add(Arrays.asList("swift", 5L));
//        dataList.add(Arrays.asList("java", 6L));
//        dataList.add(Arrays.asList("spring", 6L));
//        dataList.add(Arrays.asList("python", 7L));
//        dataList.add(Arrays.asList("javascript", 7L));
//        dataList.add(Arrays.asList("java", 8L));
//        dataList.add(Arrays.asList("spring", 8L));
//        dataList.add(Arrays.asList("javascript", 8L));
//        dataList.add(Arrays.asList("java", 9L));
//        dataList.add(Arrays.asList("spring", 9L));
//        dataList.add(Arrays.asList("kotlin", 10L));
//
//        dataList.forEach(data -> {
//            Moim moim = moimRepository.findById((Long) data.get(1)).orElse(null);
//
//            moimLanguageRepository.save(
//                    MoimLanguage.builder()
//                            .name(data.get(0).toString())
//                            .moim(moim)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("프로젝트 출시 플랫 추가")
//    @Order(24)
//    void insertMoimProjectPlatform() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 플랫폼 번호, 모임번호
//        dataList.add(Arrays.asList(1, 1L));
//        dataList.add(Arrays.asList(1, 2L));
//        dataList.add(Arrays.asList(2, 5L));
//        dataList.add(Arrays.asList(1, 6L));
//        dataList.add(Arrays.asList(3, 9L));
//        dataList.add(Arrays.asList(4, 10L));
//
//        dataList.forEach(data -> {
//            Platform platform = platformRepository.findById((Integer) data.get(0)).orElse(null);
//            Moim moim = moimRepository.findById((Long) data.get(1)).orElse(null);
//
//            moimProjectPlatformRepository.save(
//                    MoimProjectPlatform.builder()
//                            .platform(platform)
//                            .moim(moim)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("미팅 추가")
//    @Order(25)
//    void insertMeeting() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 미팅일, 미팅장소, 미팅사유, 미팅고유번호
//        dataList.add(Arrays.asList("2024-03-20 14:30:00", "서울 종로구", "회의", 1L));
//
//        dataList.forEach(data -> {
//            Moim moim = moimRepository.findById((Long) data.get(3)).orElse(null);
//
//            meetingRepository.save(
//                    Meeting.builder()
//                            .meetDate(Timestamp.valueOf(data.get(0).toString()))
//                            .addr(data.get(1).toString())
//                            .contents(data.get(2).toString())
//                            .moim(moim)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("직원 추가")
//    @Order(26)
//    void insertMember() {
//        List<List<String>> dataList = new ArrayList<>();
//        // 아이디	비밀번호	이름	우편번호	주소	전화번호	입사일	권한
//        dataList.add(Arrays.asList("admin", "1111", "뽀로로", "42250", "대구광역시 수성구", "01011111111", "2024-01-01", "ROLE_ADMIN"));
//        dataList.add(Arrays.asList("bbb", "2222", "크롱", "44069", "고양시 일산동구", "01022222222", "2024-01-01", "ROLE_EMP"));
//        dataList.add(Arrays.asList("ccc", "3333", "루피", "57630", "서울시 송파구", "01033333333", "2024-01-01", "ROLE_EMP"));
//        dataList.add(Arrays.asList("ddd", "4444", "포비", "20550", "서울시 중랑구", "01044444444", "2024-01-01", "ROLE_EMP"));
//        dataList.add(Arrays.asList("eee", "5555", "패티", "17094", "용인시 기흥구", "01055555555", "2024-01-01", "ROLE_EMP"));
//        dataList.add(Arrays.asList("fff", "6666", "짱구", "51694", "창원시 진해구", "01066666666", "2024-01-01", "ROLE_EMP"));
//        dataList.add(Arrays.asList("ggg", "7777", "유리", "48104", "부산시 해운대구", "01077777777", "2024-01-01", "ROLE_EMP"));
//        dataList.add(Arrays.asList("hhh", "8888", "훈이", "42669", "대구시 달서구", "01088888888", "2024-01-01", "ROLE_EMP"));
//        dataList.add(Arrays.asList("iii", "9999", "맹구", "49700", "서울시 광진구", "01099999999", "2024-01-01", "ROLE_EMP"));
//        dataList.add(Arrays.asList("jjj", "1010", "철수", "60880", "서울시 강남구", "01010101010", "2024-01-01", "ROLE_EMP"));
//
//        dataList.forEach(data -> {
//            memberRepository.save(
//                    Member.builder()
//                            .id(data.get(0))
//                            .pw(data.get(1))
//                            .name(data.get(2))
//                            .post(data.get(3))
//                            .addr(data.get(4))
//                            .phone(data.get(5))
//                            .hiredate(Date.valueOf(data.get(6)))
//                            .role(data.get(7))
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("직원 출퇴근 추가")
//    @Order(27)
//    void insertMemberCommute() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 출근시간, 퇴근시간, 일자, 상태, 회원번호
//        dataList.add(Arrays.asList("08:55:05", "18:05:04", "2024-01-02", "정상", 2L));
//        dataList.add(Arrays.asList("10:10:05", "18:05:04", "2024-01-02", "지각", 3L));
//        dataList.add(Arrays.asList("08:55:05", "18:05:04", "2024-01-02", "정상", 4L));
//        dataList.add(Arrays.asList("08:55:05", "18:05:04", "2024-01-02", "정상", 5L));
//        dataList.add(Arrays.asList("08:55:05", "13:05:04", "2024-01-02", "조퇴", 6L));
//        dataList.add(Arrays.asList(null, null, "2024-01-02", "연차", 7L));
//        dataList.add(Arrays.asList(null, null, "2024-01-02", "결근", 8L));
//        dataList.add(Arrays.asList("08:55:05", "18:05:04", "2024-01-02", "정상", 9L));
//        dataList.add(Arrays.asList("08:55:05", "18:05:04", "2024-01-02", "정상", 10L));
//        dataList.add(Arrays.asList("08:55:05", "18:05:04", "2024-01-02", "정상", 1L));
//
//        dataList.forEach(data -> {
//            Member member = memberRepository.findById((Long) data.get(4)).orElse(null);
//            Time inTime = data.get(0)!=null ? Time.valueOf(data.get(0).toString()) : null;
//            Time outTime = data.get(1)!=null ? Time.valueOf(data.get(1).toString()) : null;
//
//
//            memberCommuteRepository.save(
//                    MemberCommute.builder()
//                            .inTime(inTime)
//                            .outTime(outTime)
//                            .date(Date.valueOf(data.get(2).toString()))
//                            .status(data.get(3).toString())
//                            .member(member)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("직원 급여 추가")
//    @Order(28)
//    void insertMemberSalary() {
//        List<List<Object>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList(2L, 3000000));
//        dataList.add(Arrays.asList(3L, 3000000));
//        dataList.add(Arrays.asList(4L, 3000000));
//        dataList.add(Arrays.asList(5L, 3000000));
//        dataList.add(Arrays.asList(6L, 3000000));
//        dataList.add(Arrays.asList(7L, 3000000));
//        dataList.add(Arrays.asList(8L, 3000000));
//        dataList.add(Arrays.asList(9L, 3000000));
//        dataList.add(Arrays.asList(10L, 3000000));
//
//        dataList.forEach(data -> {
//            Member member = memberRepository.findById((Long) data.get(0)).orElse(null);
//
//            memberSalaryRepository.save(
//                    MemberSalary.builder()
//                            .sal((Integer) data.get(1))
//                            .member(member)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("직원 급여 사용내역 추가")
//    @Order(29)
//    void insertMemberSalaryDetail() {
//        List<List<Object>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList(3500000, 500000, "2024-02-15", 1L));
//        dataList.add(Arrays.asList(3000000, null, "2024-02-15", 2L));
//        dataList.add(Arrays.asList(3000000, null, "2024-02-15", 3L));
//        dataList.add(Arrays.asList(3000000, null, "2024-02-15", 4L));
//        dataList.add(Arrays.asList(3000000, null, "2024-02-15", 5L));
//        dataList.add(Arrays.asList(3000000, null, "2024-02-15", 6L));
//        dataList.add(Arrays.asList(3000000, null, "2024-02-15", 7L));
//        dataList.add(Arrays.asList(3000000, null, "2024-02-15", 8L));
//        dataList.add(Arrays.asList(3000000, null, "2024-02-15", 9L));
//
//        dataList.forEach(data -> {
//            MemberSalary memberSalary = memberSalaryRepository.findById((Long) data.get(3)).orElse(null);
//
//            memberSalaryDetailRepository.save(
//                    MemberSalaryDetail.builder()
//                            .paySal((Integer) data.get(0))
//                            .payComm((Integer) data.get(1))
//                            .payDate(Date.valueOf(data.get(2).toString()))
//                            .memberSalary(memberSalary)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("스터디카페 추가")
//    @Order(30)
//    void insertStudycafe() {
//        List<List<Object>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList("어깨동무 스터디 카페 종로점", "스터디 카페 종로점입니다.", "3134", "서울 종로구 율곡로10길 105", "https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg", 126.9922303, 37.5729361, 2L));
//        dataList.add(Arrays.asList("어깨동무 스터디 카페 강남점", "스터디 카페 강남점입니다.", "6130", "서울 강남구 테헤란로7길 21", "https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg", 127.0299804, 37.5009602, 3L));
//        dataList.add(Arrays.asList("어깨동무 스터디 카페 서대문구점", "스터디 카페 서대문구점입니다.", "3718", "서울 서대문구 연희로 248", "https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg", 126.9364938, 37.5792596, 4L));
//        dataList.add(Arrays.asList("어깨동무 스터디 카페 용인점", "스터디 카페 용인점입니다.", "17019", "경기 용인시 처인구 중부대로 1199", "https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg", 127.1775117, 37.2408414, 5L));
//        dataList.add(Arrays.asList("어깨동무 스터디 카페 인천점", "스터디 카페 인천점입니다.", "21554", "인천 남동구 정각로 29", "https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg", 126.705206, 37.456255, 6L));
//        dataList.add(Arrays.asList("어깨동무 스터디 카페 대구점", "스터디 카페 대구점입니다.", "41939", "대구 중구 공평로10길 25", "https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg", 128.601256, 35.8686883, 7L));
//        dataList.add(Arrays.asList("어깨동무 스터디 카페 부산점", "스터디 카페 부산점입니다.", "47545", "부산 연제구 중앙대로 1001", "https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg", 129.0745963, 35.1800626, 8L));
//        dataList.add(Arrays.asList("어깨동무 스터디 카페 광주점", "스터디 카페 광주점입니다.", "61945", "광주 서구 내방로 111", "https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg", 126.8513072, 35.1599785, 9L));
//        dataList.add(Arrays.asList("어깨동무 스터디 카페 대전점", "스터디 카페 대전점입니다.", "35242", "대전 서구 둔산로 100", "https://kr.acrofan.com/news_images/2021/04/16/mark/2021041671633.jpg", 127.3848389, 36.3501707, 10L));
//
//        dataList.forEach(data -> {
//            Member member = memberRepository.findById((Long) data.get(7)).orElse(null);
//
//            studycafeRepository.save(
//                    Studycafe.builder()
//                            .name(data.get(0).toString())
//                            .shortDesc(data.get(1).toString())
//                            .post(data.get(2).toString())
//                            .addr(data.get(3).toString())
//                            .mainImg(data.get(4).toString())
//                            .x((Double) data.get(5))
//                            .y((Double) data.get(6))
//                            .member(member)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("스터디룸 추가")
//    @Order(31)
//    void insertStudyroom() {
//        List<List<Object>> roomDataList = new ArrayList<>();
//        roomDataList.add(Arrays.asList("101호", 40000, 1L));
//        roomDataList.add(Arrays.asList("102호", 50000, 1L));
//        roomDataList.add(Arrays.asList("103호", 80000, 1L));
//        roomDataList.add(Arrays.asList("101호", 50000, 2L));
//        roomDataList.add(Arrays.asList("102호", 80000, 2L));
//        roomDataList.add(Arrays.asList("101호", 50000, 3L));
//        roomDataList.add(Arrays.asList("102호", 80000, 3L));
//        roomDataList.add(Arrays.asList("101호", 50000, 4L));
//        roomDataList.add(Arrays.asList("102호", 80000, 4L));
//        roomDataList.add(Arrays.asList("101호", 50000, 5L));
//        roomDataList.add(Arrays.asList("102호", 80000, 5L));
//        roomDataList.add(Arrays.asList("101호", 50000, 6L));
//        roomDataList.add(Arrays.asList("102호", 80000, 6L));
//        roomDataList.add(Arrays.asList("101호", 50000, 7L));
//        roomDataList.add(Arrays.asList("102호", 80000, 7L));
//        roomDataList.add(Arrays.asList("101호", 50000, 8L));
//        roomDataList.add(Arrays.asList("102호", 80000, 8L));
//        roomDataList.add(Arrays.asList("101호", 50000, 9L));
//        roomDataList.add(Arrays.asList("102호", 80000, 9L));
//
//        List<List<Object>> detailDataList = new ArrayList<>();
//        detailDataList.add(Arrays.asList(4, "Y", "N", 2));
//        detailDataList.add(Arrays.asList(5, "Y", "N", 4));
//        detailDataList.add(Arrays.asList(8, "Y", "N", 8));
//        detailDataList.add(Arrays.asList(5, "Y", "N", 4));
//        detailDataList.add(Arrays.asList(8, "Y", "N", 8));
//        detailDataList.add(Arrays.asList(5, "Y", "N", 4));
//        detailDataList.add(Arrays.asList(8, "Y", "N", 8));
//        detailDataList.add(Arrays.asList(5, "Y", "N", 4));
//        detailDataList.add(Arrays.asList(8, "Y", "N", 8));
//        detailDataList.add(Arrays.asList(5, "Y", "N", 4));
//        detailDataList.add(Arrays.asList(8, "Y", "N", 8));
//        detailDataList.add(Arrays.asList(5, "Y", "N", 4));
//        detailDataList.add(Arrays.asList(8, "Y", "N", 8));
//        detailDataList.add(Arrays.asList(5, "Y", "N", 4));
//        detailDataList.add(Arrays.asList(8, "Y", "N", 8));
//        detailDataList.add(Arrays.asList(5, "Y", "N", 4));
//        detailDataList.add(Arrays.asList(8, "Y", "N", 8));
//        detailDataList.add(Arrays.asList(5, "Y", "N", 4));
//        detailDataList.add(Arrays.asList(8, "Y", "N", 8));
//
//        AtomicInteger index = new AtomicInteger();
//        roomDataList.forEach(roomData -> {
//            Integer dex = index.getAndIncrement();
//            Studycafe studycafe = studycafeRepository.findById((Long) roomData.get(2)).orElse(null);
//
//            Studyroom studyroom = Studyroom.builder()
//                    .name(roomData.get(0).toString())
//                    .price((Integer) roomData.get(1))
//                    .studycafe(studycafe)
//                    .build();
//
//            List<Object> detailData = detailDataList.get(dex);
//            StudyroomDetail studyroomDetail = StudyroomDetail.builder()
//                    .maxNum((Integer) detailData.get(0))
//                    .beam(detailData.get(1).toString())
//                    .wboard(detailData.get(2).toString())
//                    .socket((Integer) detailData.get(3))
//                    .studyroom(studyroom)
//                    .build();
//
//            studyroomDetailRepository.save(studyroomDetail);
//        });
//    }
//
//    @Test
//    @DisplayName("스터디룸 이미지 추가")
//    @Order(32)
//    void insertStudyroomImage() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 이미지경로, 순서, 룸번호
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 1L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 1L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 2L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 2L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 3L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 3L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 4L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 4L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 5L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 5L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 6L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 6L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 7L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 7L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 8L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 8L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 9L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 9L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 10L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 10L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 11L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 11L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 12L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 12L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 13L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 13L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 14L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 14L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 15L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 15L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 16L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 16L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 17L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 17L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 18L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 18L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg", 1, 19L));
//        dataList.add(Arrays.asList("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg", 2, 19L));
//
//        dataList.forEach(data -> {
//            Studyroom studyroom = studyroomRepository.findById((Long) data.get(2)).orElse(null);
//
//            studyroomImageRepository.save(
//                    StudyroomImage.builder()
//                            .path(data.get(0).toString())
//                            .seq((Integer) data.get(1))
//                            .studyroom(studyroom)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("직원 연차 추가")
//    @Order(33)
//    void insertMemberAnnual() {
//        List<List<Object>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList(1, 15, "기본 지급", 1L));
//        dataList.add(Arrays.asList(1, 15, "기본 지급", 2L));
//        dataList.add(Arrays.asList(1, 15, "기본 지급", 3L));
//        dataList.add(Arrays.asList(1, 15, "기본 지급", 4L));
//        dataList.add(Arrays.asList(1, 15, "기본 지급", 5L));
//        dataList.add(Arrays.asList(1, 15, "기본 지급", 6L));
//        dataList.add(Arrays.asList(1, 15, "기본 지급", 7L));
//        dataList.add(Arrays.asList(1, 15, "기본 지급", 8L));
//        dataList.add(Arrays.asList(1, 15, "기본 지급", 9L));
//        dataList.add(Arrays.asList(1, 15, "기본 지급", 10L));
//
//        dataList.forEach(data -> {
//            Member member = memberRepository.findById((Long) data.get(3)).orElse(null);
//
//            memberAnnualRepository.save(
//                    MemberAnnual.builder()
//                            .type((Integer) data.get(0))
//                            .num((Integer) data.get(1))
//                            .reason(data.get(2).toString())
//                            .member(member)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("직원 연차 사용 내역 추가")
//    @Order(34)
//    void insertMemberAnnualDetail() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 사용구분, 시작일, 종료일, 사용갯수, 상태, 신청자, 결정자, 연차고유번호
//        dataList.add(Arrays.asList(1, "2021-01-02", "2021-01-02", 1, 2, 7L, 1L, 7L));
//        dataList.add(Arrays.asList(1, "2021-01-02", "2021-01-07", 6, 3, 2L, 1L, 2L));
//        dataList.add(Arrays.asList(1, "2021-01-03", "2021-01-07", 5, 1, 6L, null, 6L));
//
//        dataList.forEach(data -> {
//            Member appMember; // 승인자
//            Member member = memberRepository.findById((Long) data.get(5)).orElse(null); // 신청자
//            MemberAnnual memberAnnual = memberAnnualRepository.findById((Long) data.get(7)).orElse(null);
//
//            if( data.get(6)!=null ) appMember = memberRepository.findById((Long) data.get(6)).orElse(null);
//            else                    appMember = null;
//
//            memberAnnualDetailRepository.save(
//                    MemberAnnualDetail.builder()
//                            .useCate((Integer) data.get(0))
//                            .startDate(data.get(1).toString())
//                            .endDate(data.get(2).toString())
//                            .useNum((Integer) data.get(3))
//                            .status((Integer) data.get(4))
//                            .member(member)
//                            .approvalMember(appMember)
//                            .memberAnnual(memberAnnual)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("직원 연차 반려 추가")
//    @Order(35)
//    void insertMemberAnnualReject() {
//        List<List<Object>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList("너무 길어서", 1L, 3L));
//
//        dataList.forEach(data -> {
//            Member member = memberRepository.findById((Long) data.get(1)).orElse(null);
//            MemberAnnualDetail memberAnnualDetail = memberAnnualDetailRepository.findById((Long) data.get(2)).orElse(null);
//
//            memberAnnualRejectedRepository.save(
//                    MemberAnnualRejected.builder()
//                            .content(data.get(0).toString())
//                            .member(member)
//                            .memberAnnualDetail(memberAnnualDetail)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("예약 추가")
//    @Order(36)
//    void insertReservation() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 입실일, ip, 예약자번호, 미팅번호, 스터디룸번호
//        dataList.add(Arrays.asList("2024-03-20", "111.111.1.1", 1L, 1L, 1L));
//
//        dataList.forEach(data -> {
//            Users users = userRepository.findById((Long) data.get(2)).orElse(null);
//            Meeting meeting = meetingRepository.findById((Long) data.get(3)).orElse(null);
//            Studyroom studyroom = studyroomRepository.findById((Long) data.get(4)).orElse(null);
//
//            reservationRepository.save(
//                    Reservation.builder()
//                            .checkin(Date.valueOf(data.get(0).toString()))
//                            .ip(data.get(1).toString())
//                            .users(users)
//                            .meeting(meeting)
//                            .studyroom(studyroom)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("결제 추가")
//    @Order(37)
//    void insertPayment() {
//        List<List<Object>> dataList = new ArrayList<>();
//        // 승인번호	카드번호	이름	상품명	이메일	결제금액	결제상태	결제일자	예약번호
//        dataList.add(Arrays.asList(64654414964L, "1111111111111111", "홍길동", "어깨동무 스터디 카페 종로점", "aaa@naver.com", 40000, "2024-01-15", 1L));
//
//        dataList.forEach(data -> {
//            Reservation reservation = reservationRepository.findById((Long) data.get(7)).orElse(null);
//
//            paymentRepository.save(
//                    Payment.builder()
//                            .approvalNum((Long) data.get(0))
//                            .cardNum((String) data.get(1))
//                            .name(data.get(2).toString())
//                            .productName(data.get(3).toString())
//                            .email(data.get(4).toString())
//                            .price((Integer) data.get(5))
//                            .payDate(Date.valueOf(data.get(6).toString()))
//                            .reservation(reservation)
//                            .build()
//            );
//        });
//    }
//
//    @Test
//    @DisplayName("모입 지원자 거절 추가")
//    @Order(38)
//    void insertMoimParticipantsReject() {
//        List<List<Object>> dataList = new ArrayList<>();
//        dataList.add(Arrays.asList(1L, "거절합니다."));
//
//        dataList.forEach(data -> {
//            MoimParticipants moimParticipants = moimParticipantsRepository.findById((Long) data.get(0)).orElse(null);
//
//            moimParticipantsRejectRepository.save(
//                    MoimParticipantsReject.builder()
//                            .moimParticipants(moimParticipants)
//                            .contents(data.get(1).toString())
//                            .build()
//            );
//        });
//    }
//
//}
