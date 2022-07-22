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
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS test.users" +
                "(id bigint not null auto_increment, " +
                "name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age tinyint, " +
                "PRIMARY KEY (id))";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate(sql);

        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        String sql = "Drop table if exists test.users";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate(sql);

        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(name, lastname, age) VALUES(?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.execute();

        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM test.users WHERE id = " + id + " LIMIT 1 ";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate(sql);

        } catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {
        List<User> lists = new ArrayList<>();
        String sql = "SELECT * from users";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                lists.add(user);
            }

        } catch (Exception e) {
        }
        return lists;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM test.users";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }
}