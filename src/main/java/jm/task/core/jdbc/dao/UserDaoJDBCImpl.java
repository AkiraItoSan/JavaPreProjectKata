package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection con = Util.connectToDataBase(); Statement statement = con.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF not EXISTS users_table " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(50), " +
                    " lastName VARCHAR(50), " +
                    " age INTEGER not NULL," +
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

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
