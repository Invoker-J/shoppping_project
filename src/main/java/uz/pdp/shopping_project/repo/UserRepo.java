package uz.pdp.shopping_project.repo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import uz.pdp.shopping_project.config.ConnectionPoolManager;
import uz.pdp.shopping_project.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class UserRepo {
    public static Optional<User> findByUsername(String username) {
        String query="select * from users where username = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")


                ));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<User> findByUserId(UUID userId) {
        String query="select * from users where id = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setObject(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                ));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addUser(User user) {

        String query="insert into users(username,password) values (?,?)";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setObject(1,user.getUsername());
            preparedStatement.setObject(2,user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(HttpSession session) {
        Object object = session.getAttribute("currentUser");
        if (object != null) {
            return (User) object;
        } else {
            return null;
        }
    }

    public static Optional<User> getUserByCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }
        for (Cookie cookie : request.getCookies()) {

            if (cookie.getName().equals("userId")) {
                return findByUserId(UUID.fromString(cookie.getValue()));
            }
        }
        return Optional.empty();
    }
}
