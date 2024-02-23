package kr.co.shoulf.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDTO {
    private String name;
    private String path;
}
