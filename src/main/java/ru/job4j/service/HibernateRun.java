package ru.job4j.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.model.User;

import java.util.Calendar;

/**
 * Через Hibernate в базу данных нужно выполнить следующие операции.
 * Операции нужно выполнять последовательно.
 * В качестве базы данных использовать PostgreSQL.
 * <p>
 * create a user
 * find a user
 * update a user
 * find a user
 * delete a user
 * find all users
 */
public class HibernateRun {
    public SessionFactory factory = new Configuration()
            .configure().buildSessionFactory();

    public static void main(String[] args) {
        HibernateRun run = new HibernateRun();
        run.create();
        run.find();
        run.update();
        run.find();
        run.delete();
        run.findAll();
    }

    private void create() {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(new User("Ivan", Calendar.getInstance()));
        session.getTransaction().commit();
        session.close();
    }

    private void find() {
        Session session = factory.openSession();
        System.out.println(session.createQuery("from User where id = 1").list());
        session.close();
    }

    private void update() {
        Session session = factory.openSession();
        User user = new User(1);
        user.setName("Ivan Ivanovich");
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    private void delete() {
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(new User(1));
        session.getTransaction().commit();
        session.close();
    }

    private void findAll() {
        Session session = factory.openSession();
        session.beginTransaction();
        System.out.println(session.createQuery("from User").list());
        session.close();
    }
}
