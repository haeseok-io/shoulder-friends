package kr.co.shoulf.web.service;

import kr.co.shoulf.web.entity.Reply;
import kr.co.shoulf.web.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    public List<Reply> readAll(Long boardNo) {
        return replyRepository.findByBoard_BoardNoOrderByRegdateDesc(boardNo);
    }

    public List<Reply> readChild(Long boardNo){
        return replyRepository.findByParentReplyIsNotNullAndBoard_BoardNo(boardNo);
    }

    public void write(Reply reply) {
        replyRepository.save(reply);
    }

    public List<Reply> readRecomments(Long replyNo) {
        return replyRepository.findReplyWithChildrenByReplyNo(replyNo);
    }

    public Reply readOne(Long replyNo) {
        Optional<Reply> result =replyRepository.findById(replyNo);
        Reply reply = result.get();
        return reply;
    }
}
