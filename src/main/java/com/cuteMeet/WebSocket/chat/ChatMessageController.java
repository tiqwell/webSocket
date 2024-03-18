package com.cuteMeet.WebSocket.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageServise chatMessageServise;
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage savedMessage = chatMessageServise.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.get_recipientId(), "/queue/messages",ChatNotification.builder()._id(savedMessage.get_id())._senderId(savedMessage.get_senderId())._recipientId(savedMessage.get_recipientId())._content(savedMessage.get_content()).build());
    }
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable ("senderId") String senderId, @PathVariable ("recipientId") String recipientId){
        return ResponseEntity.ok(chatMessageServise.findChatMessages(senderId, recipientId));
    }

}
