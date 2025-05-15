package ru.job4j.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FindByNameActionTest {

    private Output output;

    private Store tracker;

    private FindByNameAction findByNameAction;

    @Mock
    private Input input;

    @BeforeEach
    void setUp() {
        output = new StubOutput();
        tracker = new MemTracker();
        findByNameAction = new FindByNameAction(output);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenOneItemWasFound() {
        Item item = tracker.add(new Item("Find item"));
        when(input.askStr(any(String.class))).thenReturn(item.getName());
        findByNameAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + item + ln
        );
    }

    @Test
    public void whenTwoItemsWasFound() {
        Item firstItem = tracker.add(new Item("Find item"));
        Item secondItem = tracker.add(new Item("Find item"));
        when(input.askStr(any(String.class))).thenReturn(firstItem.getName());
        findByNameAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + firstItem + ln + secondItem + ln
        );
    }

    @Test
    public void whenItemsWasNotFound() {
        tracker.add(new Item("Find item"));
        when(input.askStr(any(String.class))).thenReturn("Item");
        findByNameAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + "Заявки с именем: Item не найдены." + ln
        );
    }
}