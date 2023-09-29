package com.example.socketchat.controller;

import com.example.socketchat.dto.ChatRoomMapUser;
import com.example.socketchat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;

    @RequestMapping(value = "/chat/list", method = RequestMethod.GET)
    public ModelAndView chattingRoom(@Param(value = "userId") String userId){

        log.info("page : Chat");
        List<ChatRoomMapUser> chatlistByUser = chatService.getChatList(userId);

        ModelAndView mav = new ModelAndView("/chat/chatlist");
        mav.addObject("chatList",chatlistByUser);
        return mav;

    }

    @RequestMapping(value = "/chat/do", method = RequestMethod.GET)
    public ModelAndView doChat(){
        log.info("page : Chat");
        return new ModelAndView("chat/chatting");
    }
}
