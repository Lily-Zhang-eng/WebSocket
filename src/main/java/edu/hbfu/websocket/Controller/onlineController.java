package edu.hbfu.websocket.Controller;

import edu.hbfu.websocket.Service.PresenceService;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class onlineController {
    private final PresenceService presenceService;
    public onlineController(PresenceService presenceService) {
        this.presenceService = presenceService;
    }
    @MessageMapping("/online/register")
    public void register(Map<String,Object> message,//监听客户端发来请求

                         @Header("simpSessionId") String sessionId){
        String nickname=String.valueOf(message.get("nickname"));

        presenceService.register(sessionId,nickname);
    }

}
