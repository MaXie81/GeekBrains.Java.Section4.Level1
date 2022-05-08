package geekgrains.java.section4.level1.mynet.util;

import geekgrains.java.section4.level1.mynet.entity.User;

public interface IUserBuilder {
    public IUserBuilder setNickname(String nickname);
    public IUserBuilder setEmail(String email);
    public User build();
}
