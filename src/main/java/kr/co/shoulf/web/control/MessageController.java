package kr.co.shoulf.web.control;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.shoulf.web.dto.MessageRequestDTO;
import kr.co.shoulf.web.entity.Message;
import kr.co.shoulf.web.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/message")
public class MessageController {
    private final MessageService messageService;

    @GetMapping(value = "/{messageNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message getOne(@PathVariable Long messageNo) {
        return messageService.readOne(messageNo);
    }

    @GetMapping(value = "/user/{userNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> userMessageList(@PathVariable Long userNo) {
        return messageService.readUserMessageList(userNo);
    }

    @GetMapping(value = "/group/{groupNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> messageDetail(@PathVariable Long groupNo) {
        return messageService.readGroup(groupNo);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message insertMessage(MessageRequestDTO messageRequestDTO, HttpServletRequest request) {
        return messageService.addOne(messageRequestDTO, request);
    }

    @PatchMapping(value = "/group/{groupNo}/receiver/{receiverNo}/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Message>> messageGroupStatusChange(@PathVariable("groupNo") Long groupNo, @PathVariable("receiverNo") Long receiverNo, @PathVariable("status") Integer beforeStatus, @RequestParam("status") Integer afterStatus) {
        List<Message> messageList = messageService.modifyMessageGroupReceiverStatus(groupNo, receiverNo, beforeStatus, afterStatus);
        return ResponseEntity.ok().body(messageList);
    }

    @PatchMapping(value = "/{messageNo}/status")
    public ResponseEntity<Message> messageStatusChange(@PathVariable Long messageNo, @RequestParam("status") Integer status) {
        Message message = messageService.modifyMessageStatus(messageNo, status);
        return ResponseEntity.ok().body(message);
    }

}
