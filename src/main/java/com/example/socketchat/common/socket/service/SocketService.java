package com.example.socketchat.common.socket.service;

import com.example.socketchat.common.dto.ChatRoom;
import com.example.socketchat.domain.ChatRoomDao;
import com.example.socketchat.domain.ChatRoomMapUserDao;
import com.example.socketchat.dto.ChatRoomMapUser;
import com.example.socketchat.entity.ChatRoomEntity;
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

    private final ChatRoomDao chatRoomDao;

    private final ChatRoomMapUserDao chatRoomMapUserDao;

    @PostConstruct
    private void init() {
        log.info("chatRooms init.");
        chatRooms =  new LinkedHashMap<>();

        List<ChatRoom> chatRoomsInDB = chatRoomDao.getChatRooms();
        if(chatRoomsInDB!=null && chatRoomsInDB.size()!=0){

            for (ChatRoom chatRoom : chatRoomsInDB) {
                chatRooms.put(chatRoom.getRoomId(),chatRoom);
            }
        }
    }
    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        log.info("chatRoomId:{}",randomId);

        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setRoomId(randomId);
        chatRoomEntity.setRoomName(name);

        Integer roomCnt = chatRoomDao.insertChatRoom(chatRoomEntity);

        if(roomCnt==1){
            ChatRoom chatRoom = ChatRoom.builder()
                    .roomId(randomId)
                    .name(name)
                    .build();
            chatRooms.put(randomId, chatRoom);
            return chatRoom;
        }

        return null;
    }

    public Integer addChatRoomMapUser(ChatRoomMapUser chatRoomMapUser){
        return chatRoomMapUserDao.insertRoomByUser(chatRoomMapUser);
    }

    public Integer deleteChatRoomMapUser(ChatRoomMapUser chatRoomMapUser){
        return chatRoomMapUserDao.deleteRoomByUser(chatRoomMapUser);
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
