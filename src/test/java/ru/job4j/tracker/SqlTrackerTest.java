package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenReplaceTrue() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item newItem = new Item("newItem");
        tracker.add(item);
        tracker.replace(item.getId(), newItem);
        assertThat(tracker.findById(item.getId())).isEqualTo(newItem);
    }

    @Test
    public void whenReplaceFalse() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item newItem = new Item("newItem");
        tracker.add(item);
        assertThat(tracker.replace(100, newItem)).isFalse();
    }

    @Test
    public void whenDeleteIsOk() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenDeleteIsNotOk() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        tracker.delete(100);
        assertThat(tracker.findById(item.getId())).isNotNull();
    }

    @Test
    public void whenFindAll() throws SQLException {
        wipeTable();
        SqlTracker tracker = new SqlTracker(connection);
        Item firstItem = new Item("firstItem");
        Item secondItem = new Item("secondItem");
        tracker.add(firstItem);
        tracker.add(secondItem);
        List<Item> expected = List.of(new Item("firstItem"), new Item("secondItem"));
        assertThat(tracker.findAll()).isEqualTo(expected);
    }

    @Test
    public void whenFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item firstItem = new Item("item");
        Item secondItem = new Item("secondItem");
        Item thirdItem = new Item("item");
        tracker.add(firstItem);
        tracker.add(secondItem);
        tracker.add(thirdItem);
        List<Item> expected = List.of(new Item("item"), new Item("item"));
        assertThat(tracker.findByName("item")).isEqualTo(expected);
    }
}