package edu.hbfu.websocket.config;

import edu.hbfu.websocket.interceptor.StompAuthChannelInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker  // 开启消息代理
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final StompAuthChannelInterceptor stompAuthChannelInterceptor;

    public WebSocketConfig( StompAuthChannelInterceptor stompAuthChannelInterceptor) {

        this.stompAuthChannelInterceptor = stompAuthChannelInterceptor;
    }

    @Override
public void registerStompEndpoints(StompEndpointRegistry endpointRegistry) {
    endpointRegistry
            .addEndpoint("/websocket-chat")
            .setAllowedOriginPatterns("*")
            .withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/queue");
    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration){
        System.out.println("开始注册");
        registration.interceptors(stompAuthChannelInterceptor);
        System.out.println("注册完成");
    }

}
