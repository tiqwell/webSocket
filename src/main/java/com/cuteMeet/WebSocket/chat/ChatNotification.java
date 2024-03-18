package com.cuteMeet.WebSocket.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ChatNotification {
    private String _id;
    private String _senderId;
    private String _recipientId;
    private String _content;
}
