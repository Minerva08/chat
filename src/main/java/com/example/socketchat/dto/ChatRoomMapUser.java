package com.example.socketchat.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatRoomMapUser {
    private String userId;
    private String roomId;
    private String roomName;
}
