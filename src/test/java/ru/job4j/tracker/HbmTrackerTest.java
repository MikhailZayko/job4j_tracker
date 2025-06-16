package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HbmTrackerTest {

    private static HbmTracker tracker = new HbmTracker();

    @AfterAll
    public static void close() {
        tracker.close();
    }

    @AfterEach
    public void deleteItems() {
        tracker.findAll()
                .forEach(item -> tracker.delete(item.getId()));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenAddTwoItemsThenFindAllReturnsBoth() {
        tracker.add(new Item("First"));
        tracker.add(new Item("Second"));
        assertThat(tracker.findAll())
                .extracting(Item::getName)
                .containsExactly("First", "Second");
    }

    @Test
    public void whenFindByIdNonExistentThenReturnsNull() {
        assertThat(tracker.findById(999)).isNull();
    }

    @Test
    public void whenReplaceExistingItemThenSuccess() {
        Item item = tracker.add(new Item("OldName"));
        Item updated = new Item("NewName");
        updated.setId(item.getId());
        boolean result = tracker.replace(item.getId(), updated);
        Item found = tracker.findById(item.getId());
        assertThat(result).isTrue();
        assertThat(found.getName()).isEqualTo("NewName");
    }

    @Test
    public void whenReplaceNonExistentItemThenFalse() {
        Item updated = new Item("NewName");
        boolean result = tracker.replace(999, updated);
        assertThat(result).isFalse();
    }

    @Test
    public void whenDeleteExistingItemThenSuccess() {
        Item item = tracker.add(new Item("ToDelete"));
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenDeleteNonExistentItemThenNoError() {
        tracker.delete(999);
        assertThat(tracker.findAll()).isEmpty();
    }

    @Test
    public void whenFindByNameThenReturnsCorrectItems() {
        Item item1 = tracker.add(new Item("CommonName"));
        Item item2 = tracker.add(new Item("CommonName"));
        tracker.add(new Item("DifferentName"));
        assertThat(tracker.findByName("CommonName"))
                .extracting(Item::getId)
                .containsExactlyInAnyOrder(item1.getId(), item2.getId());
    }

    @Test
    public void whenFindByNameNonExistentThenEmptyList() {
        assertThat(tracker.findByName("NonExistent")).isEmpty();
    }

    @Test
    public void whenDatabaseIsEmptyThenFindAllReturnsEmptyList() {
        assertThat(tracker.findAll()).isEmpty();
    }

    @Test
    public void whenDeleteAllItemsThenFindAllEmpty() {
        tracker.add(new Item("Item1"));
        tracker.add(new Item("Item2"));
        tracker.findAll().forEach(item -> tracker.delete(item.getId()));
        assertThat(tracker.findAll()).isEmpty();
    }
}