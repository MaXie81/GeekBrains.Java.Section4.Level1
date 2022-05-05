package geekgrains.java.section4.level1.mynet.controller;

import geekgrains.java.section4.level1.mynet.dto.MessageDto;
import geekgrains.java.section4.level1.mynet.dto.UserLoginDto;
import geekgrains.java.section4.level1.mynet.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/friend/{login}/{friendLogin}")
    public List<MessageDto> getChatMessageListByFriendUser(@PathVariable String login, @PathVariable String friendLogin) {
        return messageService.getChatMessageListByFriendUser(login, friendLogin);
    }
    @PostMapping("/send/{login}")
    public void sendMessageToFriend(@PathVariable String login, @RequestBody MessageDto messageDto) {
        messageService.sendToFriend(login, messageDto);
    }
}
