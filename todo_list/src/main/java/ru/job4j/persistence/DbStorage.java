package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.model.Item;

import java.util.List;
import java.util.function.Function;

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
        this.tx(
                session -> session.save(item)
        );
        return item;
    }

    public boolean update(Item item) {
        Session session = this.factory.openSession();
        session.update(item);
        session.beginTransaction().commit();
        session.close();
        return true;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Item> getAll() {
        return this.tx(
                session -> session.createQuery("from Item").list()
        );
    }

    public List<Item> getAllActual() {
        return this.tx(
                session -> session.createQuery("from Item where done = false").list()
        );
    }
}
