package com.cuteMeet.WebSocket.chatRoom;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document

public class ChatRoom {
    @Id
    private String _id;
    private String _chatId;
    private String _senderId;
    private String _recipientId;

}
