package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLOutput;
import java.util.List;

public class Main {
    static UserServiceImpl usi =new UserServiceImpl();
    public static void main(String[] args) {
        usi.saveUser("Тимошка", "Иванов", (byte)18);
        System.out.println("User с именем - Тимошка добавлен в базу данных");

        usi.saveUser("Гоша", "Ивин", (byte)19);
        System.out.println("User с именем - Гоша добавлен в базу данных");

        usi.saveUser("Акакий", "Игранов", (byte)20);
        System.out.println("User с именем - Акакий добавлен в базу данных");

        usi.saveUser("Инакентий", "Петров", (byte)21);
        System.out.println("User с именем - Инакентий добавлен в базу данных");

        List<User> text = usi.getAllUsers();
        for (User al: text) {
            System.out.println(al);
        }
        usi.cleanUsersTable();
        usi.dropUsersTable();
        usi.closeConnection();
    }
}
