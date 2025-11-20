package edu.hbfu.websocket.events;

import edu.hbfu.websocket.Service.PresenceService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class PresenceEvents{
    private final PresenceService presenceService;
    public PresenceEvents(PresenceService presenceService) {
        this.presenceService = presenceService;
    }
    @EventListener
    public void onDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        presenceService.unregister(sessionId);
    }
}
