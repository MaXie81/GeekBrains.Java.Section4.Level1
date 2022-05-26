package geekgrains.java.section4.level1.mynet.mapper;

import geekgrains.java.section4.level1.mynet.dto.UserDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper {
//    private final String URL = "jdbc:h2:file:C:\\DB\\MyNet";
//    private final String USER = "sa";
//    private final String PASSWORD = "";
    private Connection connection;

    @PostConstruct
    public void init() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:C:\\DB\\MyNet", "sa", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @PreDestroy
    public void preDestroy() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public UserDto get(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT LOGIN, PASSWORD, NICKNAME, EMAIL FROM USERS WHERE LOGIN = ?");
            statement.setString(1, login);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                UserDto user = new UserDto();
                user.setLogin(result.getNString(1));
                user.setPassword(result.getString(2));
                user.setNickname(result.getString(3));
                user.setEmail(result.getString(4));

                return user;
            }
        } catch (SQLException exception) {
            throw new RuntimeException(String.valueOf(login));
        }
        return null;
    }

    public void set(UserDto userDto) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE USERS SET PASSWORD = ?, NICKNAME = ?, EMAIL = ? WHERE LOGIN = ?");
            statement.setString(1, userDto.getPassword());
            statement.setString(2, userDto.getNickname());
            statement.setString(3, userDto.getEmail());
            statement.setString(4, userDto.getLogin());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(String.valueOf(userDto));
        }
    }

    public void add(UserDto userDto) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS(LOGIN, PASSWORD, NICKNAME, EMAIL) VALUES(?, ?, ?, ?)");
            statement.setString(1, userDto.getLogin());
            statement.setString(2, userDto.getPassword());
            statement.setString(3, userDto.getNickname());
            statement.setString(4, userDto.getEmail());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(String.valueOf(userDto));
        }
    }

    public void remove(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM USERS WHERE LOGIN = ?");
            statement.setString(1, login);

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(login);
        }
    }
}
