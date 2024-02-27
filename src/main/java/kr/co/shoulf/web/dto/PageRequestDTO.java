package kr.co.shoulf.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;       // 현재페이지
    @Builder.Default
    private int scale = 10;     // 게시물 갯수
    @Builder.Default
    private int pageScale = 10; // 페이징 갯수
    private String queryString;

    public String getQueryString() {
        if( this.queryString==null ) {
            StringBuilder builder = new StringBuilder();
            builder.append("page="+this.page);
            builder.append("&scale="+this.scale);

            this.queryString = builder.toString();
        }

        return this.queryString;
    }

    public Pageable getPageable(String ... props) { // 가변인자
        PageRequest pageRequest = PageRequest.of(this.page-1, this.scale, Sort.by(props).descending());
        return pageRequest;
    }
}
