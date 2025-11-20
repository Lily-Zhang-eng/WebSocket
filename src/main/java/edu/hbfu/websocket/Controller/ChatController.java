package edu.hbfu.websocket.Controller;

import edu.hbfu.websocket.entity.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public ChatMessage broadcast(ChatMessage message) {
        return message;
    }
}
