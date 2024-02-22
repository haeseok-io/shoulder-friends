package kr.co.shoulf.web.repository;

import kr.co.shoulf.web.entity.StudyroomDetail;
import kr.co.shoulf.web.entity.StudyroomImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudyroomImageRepositoryTest {
    @Autowired
    StudyroomDetailRepository studyroomDetailRepository;
    @Autowired
    StudyroomImageRepository studyroomImageRepository;

    @Test
    @DisplayName("스터디룸 이미지 추가")
    void testInsertStudyroomImage() {
        //ArrayList<StudyroomImage> images = new ArrayList<>();
        //List<StudyroomDetail> list = studyroomDetailRepository.findAll();
        //
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(0)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(1)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(2)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(3)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(4)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(5)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(6)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(7)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(8)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(9)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(10)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(11)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(12)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(13)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(14)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(15)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(16)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(17)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416868349.jpg").seq(1).studyroomDetail(list.get(18)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(0)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(1)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(2)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(3)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(4)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(5)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(6)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(7)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(8)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(9)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(10)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(11)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(12)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(13)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(14)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(15)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(16)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(17)).build());
        //images.add(StudyroomImage.builder().path("https://kr.acrofan.com/news_images/2021/04/16/mark/20210416703701.jpg").seq(2).studyroomDetail(list.get(18)).build());
        //
        //studyroomImageRepository.saveAll(images);
        //
        //assertEquals(38, studyroomImageRepository.count());
    }
}