package geekgrains.java.section4.level1.mynet.service;

import geekgrains.java.section4.level1.mynet.dto.MessageDto;
import geekgrains.java.section4.level1.mynet.entity.Message;
import geekgrains.java.section4.level1.mynet.repository.MessageRepository;
import geekgrains.java.section4.level1.mynet.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServise {
    private final MessageRepository messageRepository;

    public List<MessageDto> getMessageListByAuthorId(Long id) {
        List<MessageDto> messageListDto = new ArrayList<>();
        List<Message> messageList = messageRepository.findAllByAuthorUserId(id);

        if (messageList.size() != 0)
            for (Message message : messageList) {
                MessageDto messageDto = new MessageDto();
                Map.setMessageDtoFromMassege(message, messageDto);
                messageListDto.add(messageDto);
            }
        return messageListDto;
    }
}
