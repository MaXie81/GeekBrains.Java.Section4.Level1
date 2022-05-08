package geekgrains.java.section4.level1.mynet.util;

import geekgrains.java.section4.level1.mynet.dto.MessageDto;
import geekgrains.java.section4.level1.mynet.dto.UserDto;
import geekgrains.java.section4.level1.mynet.entity.Message;
import geekgrains.java.section4.level1.mynet.entity.User;

import java.util.Date;

public class Map {
    static public void setUserDtoFromUser(User user, UserDto userDto) {
        userDto.setLogin(user.getLogin());
        userDto.setPassword("");
        userDto.setNickname(user.getNickname());
        userDto.setEmail(user.getEmail());
    }
    static public void setUserFromUserDto(UserDto userDto, User user) {
        if (user.getLogin() == null) {
            user.setLogin(userDto.getLogin());
        }
        user.setPassword(userDto.getPassword());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
    }
    static public void setMessageDtoFromMassege(Message message, MessageDto messageDto) {
        messageDto.setAuthorUserLogin(message.getAuthorUser().getLogin());
        messageDto.setRecipientUserLogin(message.getRecipientUser().getLogin());
        messageDto.setBody(message.getBody());
        messageDto.setDatetime(new Date(message.getDatetime().getTime()).toString());
    }
}
