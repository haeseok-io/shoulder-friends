package kr.co.shoulf.web.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class StudyroomDTO {
    private Long studyroomNo;
    private String name;
    private Integer price;
    private Integer maxNum;
    private String beam;
    private String wboard;
    private Integer socket;
    List<MultipartFile> path;
}
