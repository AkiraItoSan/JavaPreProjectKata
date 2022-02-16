package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
        Util.connectToDataBase();
    }

    public void createUsersTable() {
        String createUsersTableSQL = "CREATE TABLE IF not EXISTS " + USERS_TABLE +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                "name VARCHAR(50),  " +
                "lastName VARCHAR(50),  " +
                "age TINYINT UNSIGNED not NULL, " +
                "PRIMARY KEY (id))";

        try (PreparedStatement statement = Util.getConnection().prepareStatement(createUsersTableSQL)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropUsersTableSQL = "DROP TABLE IF EXISTS " + USERS_TABLE;

        try (PreparedStatement statement = Util.getConnection().prepareStatement(dropUsersTableSQL)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUserSQL = "INSERT INTO " + USERS_TABLE + " (name, lastName, age) VALUES (?,?,?)";

        try (PreparedStatement statement = Util.getConnection().prepareStatement(saveUserSQL)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeUserSQL = "DELETE FROM " + USERS_TABLE + " WHERE id=?";

        try (PreparedStatement statement = Util.getConnection().prepareStatement(removeUserSQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String getAllUsersSQL = "SELECT * FROM " + USERS_TABLE;

        try (PreparedStatement statement = Util.getConnection().prepareStatement(getAllUsersSQL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("lastname"),
                        Byte.parseByte(rs.getString("age")));
                user.setId(Long.parseLong(rs.getString("id")));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String cleanUsersTableSQL = "TRUNCATE TABLE " + USERS_TABLE;

        try (PreparedStatement statement = Util.getConnection().prepareStatement(cleanUsersTableSQL)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
