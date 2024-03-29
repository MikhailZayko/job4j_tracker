package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemTrackerTest {
    @Test
    public void whenTestFindById() {
        Store store = new MemTracker();
        Item bug = new Item("Bug");
        Item item = store.add(bug);
        Item result = store.findById(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenTestFindAll() {
        Store store = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        store.add(first);
        store.add(second);
        Item result = store.findAll().get(0);
        assertThat(result.getName()).isEqualTo(first.getName());
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        Store store = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        store.add(first);
        store.add(second);
        store.add(new Item("First"));
        store.add(new Item("Second"));
        store.add(new Item("First"));
        List<Item> result = store.findByName(first.getName());
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void whenTestFindByNameCheckSecondItemName() {
        Store store = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        store.add(first);
        store.add(second);
        store.add(new Item("First"));
        store.add(new Item("Second"));
        store.add(new Item("First"));
        List<Item> result = store.findByName(second.getName());
        assertThat(result.get(1).getName()).isEqualTo(second.getName());
    }

    @Test
    public void whenReplaceItemIsSuccessful() {
        Store store = new MemTracker();
        Item item = new Item("Bug");
        store.add(item);
        int id = item.getId();
        Item updateItem = new Item("Bug with description");
        store.replace(id, updateItem);
        assertThat(store.findById(id).getName()).isEqualTo("Bug with description");
    }

    @Test
    public void whenReplaceItemIsNotSuccessful() {
        Store store = new MemTracker();
        Item item = new Item("Bug");
        store.add(item);
        Item updateItem = new Item("Bug with description");
        boolean result = store.replace(1000, updateItem);
        assertThat(store.findById(item.getId()).getName()).isEqualTo("Bug");
        assertThat(result).isFalse();
    }

    @Test
    public void whenDeleteItemIsSuccessful() {
        Store store = new MemTracker();
        Item item = new Item("Bug");
        store.add(item);
        int id = item.getId();
        store.delete(id);
        assertThat(store.findById(id)).isNull();
    }

    @Test
    public void whenDeleteItemIsNotSuccessful() {
        Store store = new MemTracker();
        Item item = new Item("Bug");
        store.add(item);
        store.delete(1000);
        assertThat(store.findById(item.getId()).getName()).isEqualTo("Bug");
    }
}