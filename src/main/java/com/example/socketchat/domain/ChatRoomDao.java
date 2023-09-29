package com.example.socketchat.domain;

import com.example.socketchat.common.dto.ChatRoom;
import com.example.socketchat.entity.ChatRoomEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface ChatRoomDao {
    Integer insertChatRoom(ChatRoomEntity chatRoomEntity);

    List<ChatRoom> getChatRooms();

}
