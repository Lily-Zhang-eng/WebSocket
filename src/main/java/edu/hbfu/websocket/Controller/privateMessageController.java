package edu.hbfu.websocket.Controller;

import edu.hbfu.websocket.Service.PresenceService;
import edu.hbfu.websocket.entity.privateMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;
@Controller
public class privateMessageController {
    private final PresenceService presenceService;
    private final SimpMessagingTemplate template;
    public privateMessageController(PresenceService presenceService, SimpMessagingTemplate template, PresenceService presenceService1, SimpMessagingTemplate template1) {
        this.presenceService = presenceService;
        this.template = template;

    }
    @MessageMapping("/pm")//message是发送发推送的消息，inAcc是获取发送方相关信息的方法类
    public void sendMessage(Map<String, Object> message, SimpMessageHeaderAccessor inAcc) {
        String fromSessionId = inAcc.getSessionId();
        String fromName = presenceService.getNicknameBySessionId(fromSessionId);
        String toName =String.valueOf(message.get("to"));
        String content =String.valueOf(message.get("content"));
        String toSessionId = presenceService.getSessionIdbyNickname(toName);
        privateMessage privateMessage = new privateMessage();
        privateMessage.setFrom(fromName);
        privateMessage.setTo(toName);
        privateMessage.setContent(content);
        //对消息头进行一些设置
        SimpMessageHeaderAccessor hdr = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        hdr.setSessionId(toSessionId);
        hdr.setLeaveMutable(true);
        //消息发送给接收方
        template.convertAndSendToUser("/queue/pm", String.valueOf(privateMessage),hdr.getMessageHeaders());
        SimpMessageHeaderAccessor hdrReceive = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        hdrReceive.setSessionId(fromSessionId);
        hdrReceive.setLeaveMutable(true);
        template.convertAndSendToUser("/queue/pm", String.valueOf(privateMessage),hdrReceive.getMessageHeaders());
    }
}
