package com.example.socketchat.common.dto;

import com.example.socketchat.common.socket.service.SocketService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, SocketService chatService) {
        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");

        }else if(chatMessage.getType().equals(ChatMessage.MessageType.OUT)){
            sessions.remove(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 나갔습니다.");

        }
        sendMessage(chatMessage, chatService);

    }

    private <T> void sendMessage(T message, SocketService socketService) {
        sessions.parallelStream()
                .forEach(session -> socketService.sendMessage(session, message));
    }
}
