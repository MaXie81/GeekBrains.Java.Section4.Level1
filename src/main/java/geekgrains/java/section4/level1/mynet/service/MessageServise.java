package geekgrains.java.section4.level1.mynet.service;

import geekgrains.java.section4.level1.mynet.dto.MessageDto;
import geekgrains.java.section4.level1.mynet.entity.Message;
import geekgrains.java.section4.level1.mynet.entity.User;
import geekgrains.java.section4.level1.mynet.repository.MessageRepository;
import geekgrains.java.section4.level1.mynet.repository.UserRepository;
import geekgrains.java.section4.level1.mynet.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServise {
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
}
