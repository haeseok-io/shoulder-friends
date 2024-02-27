package kr.co.shoulf.web.dto;

import kr.co.shoulf.web.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class StudycafeDTO {
    private Long studycafeNo;
    private String name;
    private String shortDesc;
    private String post;
    private String addr;
    private String addrDetail;
    private Double x;
    private Double y;
    private MultipartFile mainImg;
    private Member member;
}
