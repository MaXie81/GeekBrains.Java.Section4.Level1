package geekgrains.java.section4.level1.mynet.controller;

import geekgrains.java.section4.level1.mynet.dto.FriendDto;
import geekgrains.java.section4.level1.mynet.dto.FriendListDto;
import geekgrains.java.section4.level1.mynet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {
    private final UserService userService;

    @GetMapping("/{login}")
    public FriendListDto getFriendList(@PathVariable String login) {
        return userService.getFriendList(login);
    }

    @PostMapping
    public void addFriend(@RequestBody FriendDto friendDto) {
        userService.addFriend(friendDto);
    }

}
