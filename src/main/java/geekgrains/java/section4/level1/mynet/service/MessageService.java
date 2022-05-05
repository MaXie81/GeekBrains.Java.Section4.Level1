package geekgrains.java.section4.level1.mynet.service;

import geekgrains.java.section4.level1.mynet.dto.MessageDto;
import geekgrains.java.section4.level1.mynet.entity.Message;
import geekgrains.java.section4.level1.mynet.entity.User;
import geekgrains.java.section4.level1.mynet.repository.MessageRepository;
import geekgrains.java.section4.level1.mynet.repository.UserRepository;
import geekgrains.java.section4.level1.mynet.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    public List<MessageDto> getMessageListByAuthorUser(String login) {
        List<MessageDto> messageListDto = new ArrayList<>();
        User authorUser = userService.getUserByLogin(login);

        if (authorUser != null) {
            List<Message> messageList = messageRepository.findAllByAuthorUser(authorUser);
            for (Message message : messageList) {
                MessageDto messageDto = new MessageDto();
                Map.setMessageDtoFromMassege(message, messageDto);
                messageListDto.add(messageDto);
            }
        }
        return messageListDto;
    }

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
}
