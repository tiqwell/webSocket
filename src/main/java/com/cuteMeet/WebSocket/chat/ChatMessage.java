package com.cuteMeet.WebSocket.chat;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ChatMessage {
    @Id
    private String _id;
    private String _chatId;
    private String _senderId;
    private String _recipientId;
    private String _content;
    private Date _timesStamp;
}
