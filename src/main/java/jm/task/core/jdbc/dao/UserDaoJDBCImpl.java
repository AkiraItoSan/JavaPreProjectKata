package jm.task.core.jdbc.dao;

import com.sun.xml.bind.v2.TODO;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection con = Util.connectToDataBase(); Statement statement = con.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF not EXISTS users_table " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50), " +
                    " lastName VARCHAR(50), " +
                    " age TINYINT UNSIGNED not NULL," +
                    " PRIMARY KEY (id))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection con = Util.connectToDataBase(); Statement statement = con.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users_table");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = Util.connectToDataBase(); Statement statement = con.createStatement()) {
            statement.executeUpdate("INSERT INTO kataschemas.users_table (name, lastName, age) \n" +
                    "VALUES ('" + name + "','" + lastName + "','" + age + "')");

            System.out.println("User с именем – " + name + " добавлен в базу данных ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection con = Util.connectToDataBase(); Statement statement = con.createStatement()) {
            statement.executeUpdate("DELETE FROM kataschemas.users_table\n" +
                    "     WHERE id='" + id + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection con = Util.connectToDataBase(); Statement statement = con.createStatement()) {
            statement.executeQuery("SELECT * FROM kataschemas.users_table");
            ResultSet rs = statement.getResultSet();
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
        try (Connection con = Util.connectToDataBase(); Statement statement = con.createStatement()) {
            statement.executeUpdate("DELETE FROM kataschemas.users_table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
