package geekgrains.java.section4.level1.mynet.util;

import geekgrains.java.section4.level1.mynet.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserBuilder implements IUserBuilder {
    private final String login;
    private final String password;
    private String nickname;
    private String email;

    @Override
    public IUserBuilder setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    @Override
    public IUserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public User build() {
        User user = new User(login, password);
        user.setNickname(nickname);
        user.setEmail(email);
        return user;
    }
}
