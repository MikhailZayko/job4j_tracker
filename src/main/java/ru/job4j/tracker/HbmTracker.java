package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        transactionVoid(session -> session.save(item));
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        return transaction(session -> session.createQuery(
                        "UPDATE Item SET name = :fName, created = :fCreated WHERE id = :fId")
                .setParameter("fName", item.getName())
                .setParameter("fCreated", item.getCreated())
                .setParameter("fId", id)
                .executeUpdate()) > 0;
    }

    @Override
    public void delete(int id) {
        transactionVoid(session -> session.createQuery(
                        "DELETE Item WHERE id = :fId")
                .setParameter("fId", id)
                .executeUpdate());
    }

    @Override
    public List<Item> findAll() {
        return transaction(session -> session.createQuery("FROM Item", Item.class).list());
    }

    @Override
    public List<Item> findByName(String key) {
        return transaction(session -> session.createQuery("FROM Item i WHERE i.name = :fName", Item.class)
                .setParameter("fName", key)
                .list());
    }

    @Override
    public Item findById(int id) {
        return transaction(session -> session.createQuery("FROM Item i WHERE i.id = :fId", Item.class)
                .setParameter("fId", id)
                .uniqueResult());
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private <T> T transaction(Function<Session, T> command) {
        T result = null;
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            result = command.apply(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    private void transactionVoid(Consumer<Session> command) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            command.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
