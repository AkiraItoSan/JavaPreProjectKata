package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/kataschemas";
    static final String JDBS_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String USER = "root";
    static final String PASSWORD = "2202995SsIl";

    public static Connection connectToDataBase() throws SQLException {
        try {
            Class.forName(JDBS_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

    }

    public static void createUsersTable() {
            try (Connection con = connectToDataBase(); Statement statement = con.createStatement()) {
                String SQL = "CREATE TABLE IF not EXISTS users_table " +
                        "(id INTEGER not NULL, " +
                        " name VARCHAR(50), " +
                        " lastName VARCHAR(50), " +
                        " age INTEGER not NULL," +
                        " PRIMARY KEY (id))";

                statement.executeUpdate(SQL);
                System.out.println("Table successfully created...");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}