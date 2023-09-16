package com.example.socketchat.common.socket.controller;

import com.example.socketchat.common.dto.ChatRoom;
import com.example.socketchat.common.socket.service.SocketService;
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
    public ChatRoom createRoom(@RequestBody String name) {
        return chatService.createRoom(name);
    }

    @GetMapping("/socket-con-list")
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}
