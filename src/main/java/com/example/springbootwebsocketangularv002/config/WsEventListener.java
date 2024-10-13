package com.example.springbootwebsocketangularv002.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.springbootwebsocketangularv002.controller.WsChatMessage;
import com.example.springbootwebsocketangularv002.controller.WsChatMessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WsEventListener {

        private final SimpMessageSendingOperations messageSendingOperations;

    @EventListener
    public void handleWsDisconnectListener( SessionDisconnectEvent event){
        //To listen to another even, create the another method with NewEvent as argument.
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username !=null){
            log.info("User disconnected: {} ", username);
            var message = WsChatMessage.builder()
                    .type(WsChatMessageType.LEAVE)
                    .sender(username)
                    .build();
            //pass the message to the broker specific topic : public
            messageSendingOperations.convertAndSend("/topic/public",message);
        }
    }
    


}
