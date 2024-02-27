package kr.co.shoulf.web.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PageResponseDTO<E> {
    private int page;
    private int scale;
    private int pageScale;
    private int total;
    private String queryString;

    // 시작 페이지 번호
    private int start;
    private int end;

    // 이전 페이지 존재 여부
    private boolean prev;
    private boolean next;

    private List<E> dataList;

    @Builder(builderMethodName = "pageBuilder")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dataList, int total) {
        if( total<=0 )  return;

        this.page = pageRequestDTO.getPage();
        this.scale = pageRequestDTO.getScale();
        this.pageScale = pageRequestDTO.getPageScale();
        this.queryString = pageRequestDTO.getQueryString();

        this.dataList = dataList;
        this.total = total;

        this.end = (int)(Math.ceil(this.page/ (double) this.pageScale))*this.pageScale;
        this.start = (this.end-this.pageScale)+1;

        // 이 값을 넘는 갖장 작은 수
        int last = (int)(Math.ceil(this.total/(double)this.scale));

        // 데이터의 갯수를 계산한 마지막 페이지 번호
        this.end = end >= last ? last : end;

        this.prev = this.start>1;
        this.next = total>this.end*this.scale;
    }
}
