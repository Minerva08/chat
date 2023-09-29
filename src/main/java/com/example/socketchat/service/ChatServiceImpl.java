package com.example.socketchat.service;

import com.example.socketchat.domain.ChatRoomMapUserDao;
import com.example.socketchat.dto.ChatRoomMapUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRoomMapUserDao chatRoomMapUserDao;

    @Override
    public List<ChatRoomMapUser> getChatList(String userId) {
        return chatRoomMapUserDao.getchatListByUser(userId);
    }
}
