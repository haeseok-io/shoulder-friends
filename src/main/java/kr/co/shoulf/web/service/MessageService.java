package kr.co.shoulf.web.service;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.shoulf.web.dto.MessageRequestDTO;
import kr.co.shoulf.web.entity.Message;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.repository.MessageRepository;
import kr.co.shoulf.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public Long countByUserNewReceived(Long userNo) {
        return messageRepository.newReceivedCountByUserNo(userNo);
    }

    public Message readOne(Long messageNo) {
        return messageRepository.findById(messageNo).orElse(null);
    }

    public List<Message> readUserMessageList(Long userNo) {
        return messageRepository.selectByUserNoGroupByGroupNoOrderByMessageNoDesc(userNo);
    }

    public List<Message> readGroup(Long groupNo) {
        return messageRepository.findByGroupNo(groupNo);
    }

    public Message addOne(MessageRequestDTO messageRequestDTO, HttpServletRequest request) {
        Users sender = userRepository.findById(messageRequestDTO.getSenderNo()).orElse(null);
        Users receiver = userRepository.findById(messageRequestDTO.getReceiverNo()).orElse(null);
        Message responseMessage = null;

        if( sender!=null && receiver!=null ) {
            Long maxGroupNo = messageRepository.maxByGroupNo();
            Long groupNo = messageRequestDTO.getGroupNo()!=null ? messageRequestDTO.getGroupNo() : maxGroupNo+1L;


            Message requestMessage = Message.builder()
                    .groupNo(groupNo)
                    .contents(messageRequestDTO.getContents())
                    .sender(sender)
                    .receiver(receiver)
                    .ip(request.getRemoteAddr())
                    .build();
            responseMessage = messageRepository.save(requestMessage);
        }

        return responseMessage;
    }

    public List<Message> modifyMessageGroupReceiverStatus(Long groupNo, Long receiverNo, Integer beforeStatus, Integer afterStatus) {
        return messageRepository.findByGroupNoAndReceiver_UserNoAndStatus(groupNo, receiverNo, beforeStatus).stream().map(message -> {
            System.out.println(message);
            message.setStatus(afterStatus);
            messageRepository.save(message);
            return message;
        }).toList();
    }

    public Message modifyMessageStatus(Long messageNo, Integer status) {
        Message message = messageRepository.findById(messageNo).orElse(null);
        if( message!=null ) {
            message.setStatus(status);
            messageRepository.save(message);
        }

        return message;
    }
}
