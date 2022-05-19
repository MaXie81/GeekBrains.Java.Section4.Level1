package geekgrains.java.section4.level1.mynet.mapper;

import geekgrains.java.section4.level1.mynet.dto.UserDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;

@Component
public class UserDtoMapper {
    private final String URL = "jdbc:h2:file:C:\\DB\\MyNet";
    private final String USER = "sa";
    private final String PASSWORD = "";
    private Connection connection;

    @PostConstruct
    public void init() {
        try {
            connection = DriverManager.getConnection(URL, USER,PASSWORD);
            System.out.println("!!! " + connection.isClosed());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @PreDestroy
    public void preDestroy() {
        try {
            connection.close();
            System.out.println("!!! " + connection.isClosed());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public UserDto getUser(String login) {
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

    public void setUser(UserDto userDto) {
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

    public void addUser(UserDto userDto) {
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

    public void deleteUser(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM USERS WHERE LOGIN = ?");
            statement.setString(1, login);

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(login);
        }
    }
}
