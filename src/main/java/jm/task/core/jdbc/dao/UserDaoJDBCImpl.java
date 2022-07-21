package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.CallableStatementWrapper;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        connection = new Util().getConnection();
        try {
            connection.createStatement().execute("CREATE TABLE if not exists Users" +
                    "(ID int PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255), " +
                    "lastname VARCHAR(255), " +
                    "age INT);");

        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        connection = new Util().getConnection();
        try {
            connection.prepareStatement("drop table Users;").execute();
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        connection = new Util().getConnection();
        try (PreparedStatement qwer = connection.prepareStatement("INSERT INTO Users (name, lastname,age) VALUES (?,?,?)")) {
            qwer.setString(1, name);
            qwer.setString(2, lastName);
            qwer.setInt(3, age);
            qwer.execute();

        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        connection = new Util().getConnection();
        try (PreparedStatement a = new CallableStatementWrapper("DELETE FROM Users where id = " + id)){
            connection.prepareStatement("DELETE FROM Users where id = " + id).execute();

        } catch (SQLException e) {
        }

    }

    public List<User> getAllUsers() {
        connection = new Util().getConnection();
        List<User> result = new ArrayList<>();
        try {
            ResultSet res = connection.createStatement().executeQuery("Select * from Users;");
            while (res.next()) {
                User usper = new User(res.getString("name"),
                        res.getString("lastName"),
                        res.getByte("age"));
                usper.setId(res.getLong("ID"));
                result.add(usper);
            }
            return result;
        } catch (SQLException e) {}
        return null;
    }

    public void cleanUsersTable() {
        connection = new Util().getConnection();
        try {
            connection.createStatement().execute("TRUNCATE TABLE Users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
