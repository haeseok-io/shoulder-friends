package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.Position;
import kr.co.shoulf.web.entity.PositionDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PositionDetailRepositoryTest {
    @Autowired
    private PositionDetailRepository positionDetailRepository;
    @Autowired
    private PositionRepository positionRepository;

    @Test
    @DisplayName("직무 중분류 추가")
    void testInsertPositionDetail() {
        ArrayList<PositionDetail> test = new ArrayList<>();
        List<Position> position = positionRepository.findAll();

        test.add(PositionDetail.builder().position(position.get(0)).middleName("UI/UX 기획").build());
        test.add(PositionDetail.builder().position(position.get(0)).middleName("게임기획").build());
        test.add(PositionDetail.builder().position(position.get(0)).middleName("프로젝트 매니저").build());
        test.add(PositionDetail.builder().position(position.get(0)).middleName("프로덕트 매니저/오너").build());
        test.add(PositionDetail.builder().position(position.get(0)).middleName("컨텐트 기획").build());
        test.add(PositionDetail.builder().position(position.get(0)).middleName("하드웨어(제품) 기획").build());
        test.add(PositionDetail.builder().position(position.get(0)).middleName("기타").build());
        test.add(PositionDetail.builder().position(position.get(1)).middleName("그래픽디자인").build());
        test.add(PositionDetail.builder().position(position.get(1)).middleName("UI/UX디자인").build());
        test.add(PositionDetail.builder().position(position.get(1)).middleName("3D 디자인").build());
        test.add(PositionDetail.builder().position(position.get(1)).middleName("도트 디자인").build());
        test.add(PositionDetail.builder().position(position.get(1)).middleName("모션/이펙트 디자인").build());
        test.add(PositionDetail.builder().position(position.get(1)).middleName("BX/브랜딩 디자인").build());
        test.add(PositionDetail.builder().position(position.get(1)).middleName("기타").build());
        test.add(PositionDetail.builder().position(position.get(2)).middleName("IOS").build());
        test.add(PositionDetail.builder().position(position.get(2)).middleName("안드로이드").build());
        test.add(PositionDetail.builder().position(position.get(2)).middleName("웹프론트엔드").build());
        test.add(PositionDetail.builder().position(position.get(2)).middleName("웹퍼블리셔").build());
        test.add(PositionDetail.builder().position(position.get(2)).middleName("크로스플랫폼").build());
        test.add(PositionDetail.builder().position(position.get(2)).middleName("게임클라이언트").build());
        test.add(PositionDetail.builder().position(position.get(2)).middleName("설치형 소프트웨어(비게임)").build());
        test.add(PositionDetail.builder().position(position.get(2)).middleName("인베디드 SW").build());
        test.add(PositionDetail.builder().position(position.get(3)).middleName("웹서버").build());
        test.add(PositionDetail.builder().position(position.get(3)).middleName("블록체인").build());
        test.add(PositionDetail.builder().position(position.get(3)).middleName("AI").build());
        test.add(PositionDetail.builder().position(position.get(3)).middleName("DB/빅데이터/DS").build());
        test.add(PositionDetail.builder().position(position.get(3)).middleName("게임 서버").build());
        test.add(PositionDetail.builder().position(position.get(3)).middleName("프롬프트 엔지니어").build());
        test.add(PositionDetail.builder().position(position.get(3)).middleName("아키텍트(TA/DA/AA)").build());
        test.add(PositionDetail.builder().position(position.get(3)).middleName("보안/화이트 해커").build());
        test.add(PositionDetail.builder().position(position.get(4)).middleName("사업기획 (BD/BA)").build());
        test.add(PositionDetail.builder().position(position.get(4)).middleName("마케팅").build());
        test.add(PositionDetail.builder().position(position.get(4)).middleName("재무/회계").build());
        test.add(PositionDetail.builder().position(position.get(4)).middleName("영업").build());
        test.add(PositionDetail.builder().position(position.get(4)).middleName("전략/컨설팅").build());
        test.add(PositionDetail.builder().position(position.get(4)).middleName("투자/고문").build());
        test.add(PositionDetail.builder().position(position.get(4)).middleName("인사(HR)").build());
        test.add(PositionDetail.builder().position(position.get(4)).middleName("기타").build());
        test.add(PositionDetail.builder().position(position.get(5)).middleName("작가/블로거").build());
        test.add(PositionDetail.builder().position(position.get(5)).middleName("인플루언서/스트리머").build());
        test.add(PositionDetail.builder().position(position.get(5)).middleName("법률/노무").build());
        test.add(PositionDetail.builder().position(position.get(5)).middleName("의료/의학").build());
        test.add(PositionDetail.builder().position(position.get(5)).middleName("요식업/쉐프").build());
        test.add(PositionDetail.builder().position(position.get(5)).middleName("프로듀서/CP").build());
        test.add(PositionDetail.builder().position(position.get(5)).middleName("작곡(사운드)").build());
        test.add(PositionDetail.builder().position(position.get(5)).middleName("영상").build());
        test.add(PositionDetail.builder().position(position.get(5)).middleName("운영").build());
        test.add(PositionDetail.builder().position(position.get(5)).middleName("QA").build());
        test.add(PositionDetail.builder().position(position.get(5)).middleName("기타").build());

        test.forEach(positionDetail -> positionDetailRepository.save(positionDetail));

    }

}