package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StartUITest {
    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                Arrays.asList("0", "Item name", "1")
        );
        Store store = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new CreateAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, store, actions);
        assertThat(store.findAll().get(0).getName()).isEqualTo("Item name");
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        Store store = new MemTracker();
        Item item = store.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                Arrays.asList("0", String.valueOf(item.getId()), replacedName, "1")
        );
        List<UserAction> actions = Arrays.asList(
                new ReplaceAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, store, actions);
        assertThat(store.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Store store = new MemTracker();
        Item item = store.add(new Item("Deleted item"));
        Input in = new StubInput(
                Arrays.asList("0", String.valueOf(item.getId()), "1")
        );
        List<UserAction> actions = Arrays.asList(
                new DeleteAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, store, actions);
        assertThat(store.findById(item.getId())).isNull();
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Store store = new MemTracker();
        Item one = store.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(
                Arrays.asList("0", String.valueOf(one.getId()), replaceName, "1")
        );
        List<UserAction> actions = Arrays.asList(
                new ReplaceAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, store, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu." + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
                        + "=== Edit item ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Menu." + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
        );
    }

    @Test
    public void whenShowAllItemsTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Store store = new MemTracker();
        Item one = store.add(new Item("test1"));
        Item two = store.add(new Item("test2"));
        Input in = new StubInput(Arrays.asList("0", "1"));
        List<UserAction> actions = Arrays.asList(
                new FindAllAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, store, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu." + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Show all items ===" + ln
                        + one + ln
                        + two + ln
                        + "Menu." + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
        );
    }

    @Test
    public void whenFindByNameTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Store store = new MemTracker();
        Item one = store.add(new Item("test1"));
        Item two = store.add(new Item("test1"));
        Input in = new StubInput(Arrays.asList("0", one.getName(), "1"));
        List<UserAction> actions = Arrays.asList(
                new FindByNameAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, store, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu." + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Find items by name ===" + ln
                        + one + ln
                        + two + ln
                        + "Menu." + ln
                        + "0. Find items by name" + ln
                        + "1. Exit Program" + ln
        );
    }

    @Test
    public void whenFindByIdTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Store store = new MemTracker();
        Item one = store.add(new Item("test1"));
        Input in = new StubInput(Arrays.asList("0", String.valueOf(one.getId()), "1"));
        List<UserAction> actions = Arrays.asList(
                new FindByIdAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, store, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu." + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Find item by id ===" + ln
                        + one + ln
                        + "Menu." + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
        );
    }

    @Test
    void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                Arrays.asList("3", "0")
        );
        Store store = new MemTracker();
        List<UserAction> actions = List.of(
                new ExitAction(out)
        );
        new StartUI(out).init(in, store, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu." + ln
                        + "0. Exit Program" + ln
                        + "Неверный ввод, вы можете выбрать: 0 .. 0" + ln
                        + "Menu." + ln
                        + "0. Exit Program" + ln
        );
    }
}