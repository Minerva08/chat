package com.example.socketchat.service;

import com.example.socketchat.dto.ChatRoomMapUser;

import java.util.List;

public interface ChatService {
    List<ChatRoomMapUser> getChatList(String userId);
}
