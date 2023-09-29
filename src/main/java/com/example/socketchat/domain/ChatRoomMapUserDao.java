package com.example.socketchat.domain;

import com.example.socketchat.dto.ChatRoomMapUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomMapUserDao {
    Integer insertRoomByUser(ChatRoomMapUser chatRoomMapUser);

    Integer deleteRoomByUser(ChatRoomMapUser chatRoomMapUser);

    List<ChatRoomMapUser> getchatListByUser(String userId);
}
