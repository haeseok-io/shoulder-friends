package kr.co.shoulf.web.websocket.handler;

import kr.co.shoulf.web.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Component
@RequiredArgsConstructor
public class MessageSocketHandler extends TextWebSocketHandler {
    private final MessageService messageService;
    List<Map<String, Object>> list = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        JSONObject data = jsonObjectParser(message.getPayload());

        // 새로운 회원 초기화
        if( data.get("type").equals("initUser") ) {
            Long userNo = Long.parseLong(data.get("userNo").toString());

            boolean flag = false;
            Integer idx = list.size();

            if( list.size()>0 ) {
                for(int i=0; i<list.size(); i++) {
                    Long uNo = (Long) list.get(i).get("userNo");
                    if( uNo.equals(userNo) ) {
                        flag = true;
                        idx = i;
                        break;
                    }
                }
            }

            // 존재하는 회원이라면 세션정보 업데이트
            if( flag ) {
                Map<String, Object> map = list.get(idx);
                map.put("sessionId", session.getId());
                map.put("session", session);
            }
            // 존재하지 않는 회원이라면 세션정보와 회원번호 추가
            else {
                Map<String, Object> map = new HashMap<>();
                map.put("userNo", userNo);
                map.put("sessionId", session.getId());
                map.put("session", session);
                list.add(map);
            }
        }

        // 새로운 메세지를 체크
        if( data.get("type").equals("requestMessageCount") ) {
            Long userNo = (Long) data.get("userNo");

            Integer idx = getUserMapIndex(userNo);

            if( idx!=null ) {
                Map<String, Object> userMap = list.get(idx);

                Map<String, Object> map = new HashMap<>();
                map.put("type", "responseMessageCount");
                map.put("count", messageService.countByUserNewReceived(userNo));

                WebSocketSession webSocketSession = (WebSocketSession) userMap.get("session");
                if( webSocketSession!=null ) {
                    JSONObject jsonObject = new JSONObject(map);
                    webSocketSession.sendMessage(new TextMessage(jsonObject.toJSONString()));
                }
            }
        }

        // 최근 메세지 리스트 가져오기
        if( data.get("type").equals("requestMessageSend") ) {
            Long senderNo = (Long) data.get("senderNo");
            Long receiverNo = (Long) data.get("receiverNo");

            Integer receiverSocketIndex = getUserMapIndex(receiverNo);
            if( receiverSocketIndex!=null ) {
                Map<String, Object> receiverMap = list.get(receiverSocketIndex);

                Map<String, Object> map = new HashMap<>();
                map.put("type", "responseMessageSend");
                map.put("groupNo", data.get("groupNo"));
                map.put("messageNo", data.get("messageNo"));

                WebSocketSession webSocketSession = (WebSocketSession) receiverMap.get("session");
                if( webSocketSession!=null ) {
                    JSONObject jsonObject = new JSONObject(map);
                    webSocketSession.sendMessage(new TextMessage(jsonObject.toJSONString()));
                }
            }

            System.out.println(receiverSocketIndex);
            //System.out.println(data);
            //Long userNo = (Long) data.get("userNo");
            //messageService.readUserMessageList(userNo);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        // 소켓이 종료되면 해당 세션들을 찾아서 제거
        if( list.size()>0 ) {
            Iterator<Map<String, Object>> iterator = list.iterator();
            while( iterator.hasNext() ) {
                Map<String, Object> map = iterator.next();
                if( map.containsKey("sessionId") && map.get("sessionId").equals(session.getId()) ) {
                    iterator.remove();
                }
            }
        }

        super.afterConnectionClosed(session, status);
    }

    private Integer getUserMapIndex(Long userNo) {
        Integer idx = null;

        if( list.size()>0 ) {
            for(int i=0; i<list.size(); i++) {
                Long uNo = (Long) list.get(i).get("userNo");

                if( uNo.equals(userNo) ) {
                    idx = i;
                    break;
                }
            }
        }

        return idx;
    }

    private JSONObject jsonObjectParser(String jsonStr) {
        JSONObject obj = null;
        JSONParser jsonParser = new JSONParser();

        try {
            obj = (JSONObject) jsonParser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
