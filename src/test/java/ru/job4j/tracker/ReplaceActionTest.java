package ru.job4j.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ReplaceActionTest {

    private Output output;

    private Store tracker;

    private ReplaceAction replaceAction;

    @Mock
    private Input input;

    @BeforeEach
    void setUp() {
        output = new StubOutput();
        tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        replaceAction = new ReplaceAction(output);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenItemWasReplacedSuccessfully() {
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn("New item name");
        replaceAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Edit item ===" + ln
                        + "Заявка изменена успешно." + ln
        );
    }

    @Test
    public void whenItemWasNotReplaced() {
        replaceAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Edit item ===" + ln
                        + "Ошибка замены заявки." + ln
        );
    }
}