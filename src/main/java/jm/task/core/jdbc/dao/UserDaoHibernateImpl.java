package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("create TABLE if not exists User (ID int PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255)," +
                    "lastname VARCHAR(255)," +
                    "age TINYINT ); ").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void closeConnection() {

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User ").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User usr = new User();
            usr.setId(id);
            session.remove(usr);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            result = session.createQuery("FROM User ").getResultList();
            transaction.commit();
            session.close();
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User ").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
