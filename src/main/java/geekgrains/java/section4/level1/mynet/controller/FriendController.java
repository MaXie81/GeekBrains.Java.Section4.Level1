package geekgrains.java.section4.level1.mynet.controller;

import geekgrains.java.section4.level1.mynet.dto.UserFriendDto;
import geekgrains.java.section4.level1.mynet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {
    private final UserService userService;

    @GetMapping("/{login}")
    public UserFriendDto getFriendList(@PathVariable String login) {
        return userService.getFriendList(login);
    }

}
