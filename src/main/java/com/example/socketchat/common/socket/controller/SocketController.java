package com.example.socketchat.common.socket.controller;

import com.example.socketchat.common.dto.ChatRoom;
import com.example.socketchat.common.socket.service.SocketService;
import com.example.socketchat.dto.ChatRoomInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SocketController {
    private final SocketService chatService;

    @PostMapping("/socket-join")
    public ChatRoom createRoom(@RequestBody ChatRoomInfo info) {
        return chatService.createRoom(info.getRoomName());
    }

    @GetMapping("/socket-con-list")
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}
