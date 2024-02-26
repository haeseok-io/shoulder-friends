package kr.co.shoulf.web.dto;

import kr.co.shoulf.web.entity.Board;
import kr.co.shoulf.web.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private Board board;
    private Reply reply;
}
