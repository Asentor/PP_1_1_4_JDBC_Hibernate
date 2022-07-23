package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    static Connection connection = null;
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {

        }

        return connection;
    }
    public static void closeConnection() {

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.setProperty(Environment.JDBC_TIME_ZONE, "UTC+5").setProperty(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest")
                        .setProperty(Environment.USER, "root")
                        .setProperty(Environment.PASS, "1234")
                        .setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
                        .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
                        .setProperty(Environment.HBM2DDL_AUTO, "update")
                        .addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory((org.hibernate.service.ServiceRegistry) serviceRegistry);
            } catch (HibernateException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
        return sessionFactory;
    }
}

