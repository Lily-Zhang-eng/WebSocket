package edu.hbfu.websocket.Service;

import org.apache.catalina.User;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class PresenceService {
    private  final DualHashBidiMap<String,String> onlineList = new DualHashBidiMap<>();
    private final SimpMessagingTemplate messagingTemplate;
    public PresenceService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;

    }
    public void register(String sessionId, String nickname) {
        if (nickname == null){
            nickname="user"+sessionId;
        }
        onlineList.put(sessionId,nickname);
        broadcast();

    }
    public  void unregister(String sessionId) {
        onlineList.remove(sessionId);
        broadcast();

    }
    public String getSessionIdbyNickname(String nickname) {
        return onlineList.getKey(nickname);
    }
    public String getNicknameBySessionId(String sessionId) {
        return onlineList.get(sessionId);
    }
    public List<String> getOnlineList() {
        return new ArrayList<>(onlineList.values());
    }
    public void broadcast(){
        messagingTemplate.convertAndSend("/topic/onlineUser",getOnlineList());
    }


}
