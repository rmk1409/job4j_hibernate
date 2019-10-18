package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.model.Item;

import java.util.List;

public class DbStorage {
    public SessionFactory factory;
    private final static DbStorage INSTANCE = new DbStorage();

    private DbStorage() {
        this.factory = new Configuration()
                .configure().buildSessionFactory();
    }

    public static DbStorage getINSTANCE() {
        return INSTANCE;
    }

    public Item add(Item item) {
        Session session = this.factory.openSession();
        session.save(item);
        session.beginTransaction().commit();
        session.close();
        return item;
    }

    public boolean update(Item item) {
        Session session = this.factory.openSession();
        session.update(item);
        session.beginTransaction().commit();
        session.close();
        return true;
    }

    public List<Item> getAll() {
        Session session = this.factory.openSession();
        List<Item> result = session.createQuery("from Item").list();
        session.close();
        return result;
    }

    public List<Item> getAllActual() {
        Session session = this.factory.openSession();
        List<Item> result = session.createQuery("from Item where done = false").list();
        session.close();
        return result;
    }
}
