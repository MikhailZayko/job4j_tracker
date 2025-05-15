package ru.job4j.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FindByIdActionTest {

    private Output output;

    private Store tracker;

    private FindByIdAction findByIdAction;

    @Mock
    private Input input;

    @BeforeEach
    void setUp() {
        output = new StubOutput();
        tracker = new MemTracker();
        findByIdAction = new FindByIdAction(output);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenItemWasFoundSuccessfully() {
        Item item = tracker.add(new Item("Find item"));
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        findByIdAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + item + ln
        );
    }

    @Test
    public void whenItemWasNotFound() {
        when(input.askInt(any(String.class))).thenReturn(2);
        findByIdAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + "Заявка с введенным id: 2 не найдена." + ln
        );
    }
}