package com.cuteMeet.WebSocket.chatRoom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIdNotExists){
        return  chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId).map(ChatRoom::get_chatId).or(()->{
            if (createNewRoomIdNotExists){
                var chatId = createChatId(senderId, recipientId);
                return Optional.of(chatId);
            }
            return Optional.empty();
        });
    }

    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);
        ChatRoom senderRecipient = ChatRoom.builder()._chatId(chatId)._senderId(senderId)._recipientId(recipientId).build();
        ChatRoom recipientSender = ChatRoom.builder()._chatId(chatId)._senderId(recipientId)._recipientId(senderId).build();
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return null;
    }
}
