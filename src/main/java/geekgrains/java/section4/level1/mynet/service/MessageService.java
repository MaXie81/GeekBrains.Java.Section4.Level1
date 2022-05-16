package geekgrains.java.section4.level1.mynet.service;

import geekgrains.java.section4.level1.mynet.dto.MessageDto;
import geekgrains.java.section4.level1.mynet.entity.Message;
import geekgrains.java.section4.level1.mynet.entity.User;
import geekgrains.java.section4.level1.mynet.repository.MessageRepository;
import geekgrains.java.section4.level1.mynet.repository.UserRepository;
import geekgrains.java.section4.level1.mynet.util.Map;
import geekgrains.java.section4.level1.mynet.util.MessageIteratorCode;
import geekgrains.java.section4.level1.mynet.util.MessageList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    public List<MessageDto> getChatMessageListByFriendUser(String login, String friendLogin) {
        List<MessageDto> messageListDto = new ArrayList<>();
        User authorUser = userService.getUserByLogin(login);
        User recipientUser = userService.getUserByLogin(friendLogin);

        if (authorUser != null & recipientUser != null) {
            List<Message> messageList = messageRepository.findAllByAuthorUserAndRecipientUser(authorUser, recipientUser);
            messageList.addAll(messageRepository.findAllByAuthorUserAndRecipientUser(recipientUser, authorUser));
            messageList.sort((o1, o2) -> o1.getDatetime().compareTo(o2.getDatetime()));
            for (Message message : messageList) {
                MessageDto messageDto = new MessageDto();
                Map.setMessageDtoFromMassege(message, messageDto);
                messageListDto.add(messageDto);
            }
        }
        return messageListDto;
    }

    public List<MessageDto> getAllChatMessageList(String login) {
        List<MessageDto> messageListDto = new ArrayList<>();
        User authorUser = userService.getUserByLogin(login);

        if (authorUser != null) {
            MessageList messageList = new MessageList(messageRepository.findAllByAuthorUserOrRecipientUser(authorUser, authorUser), MessageIteratorCode.DIALOG);
            for (Message message : messageList) {
                MessageDto messageDto = new MessageDto();
                Map.setMessageDtoFromMassege(message, messageDto);
                messageListDto.add(messageDto);
            }
        }
        return messageListDto;
    }

    public void sendToFriend(String login, MessageDto messageListDto) {
        Message message = new Message();
        User authorUser = userService.getUserByLogin(login);
        User recipientUser = userService.getUserByLogin(messageListDto.getRecipientUserLogin());

        if (authorUser != null & recipientUser != null) {
            message.setAuthorUser(authorUser);
            message.setRecipientUser(recipientUser);
            message.setBody(messageListDto.getBody());
            message.setDatetime(new Timestamp(System.currentTimeMillis()));

            messageRepository.save(message);
        }
    }
}
