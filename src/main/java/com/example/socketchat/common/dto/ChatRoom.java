package com.example.socketchat.common.dto;

import com.example.socketchat.common.socket.service.SocketService;
import com.example.socketchat.dto.ChatRoomMapUser;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@Slf4j
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
        for (WebSocketSession webSocketSession : sessions) {
            log.info("chatRoom session:{}",webSocketSession);
        }

        log.info("session Info:{}",session.toString());

        ChatRoomMapUser chatRoomMapUser = new ChatRoomMapUser();
        chatRoomMapUser.setUserId(chatMessage.getSender());
        chatRoomMapUser.setRoomId(chatMessage.getRoomId());

        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장 했습니다.");

            chatService.addChatRoomMapUser(chatRoomMapUser);

        }else if(chatMessage.getType().equals(ChatMessage.MessageType.OUT)){
            sessions.remove(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 나갔습니다.");
            chatService.deleteChatRoomMapUser(chatRoomMapUser);

        }
        sendMessage(chatMessage, chatService);

    }

    private <T> void sendMessage(T message, SocketService socketService) {
        sessions.parallelStream()
                .forEach(session -> socketService.sendMessage(session, message));
    }
}
