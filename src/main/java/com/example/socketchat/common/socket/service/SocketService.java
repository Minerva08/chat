package com.example.socketchat.common.socket.service;

import com.example.socketchat.common.dto.ChatRoom;
import com.example.socketchat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocketService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    private final ChatService chatService;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }
    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String id) {
        String randomId = UUID.randomUUID().toString();
        log.info("chatRoomId:{}",randomId);
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .name(id)
                .build();
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
