package ru.job4j.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DeleteActionTest {

    private Output output;

    private Store tracker;

    private DeleteAction deleteAction;

    @Mock
    private Input input;

    @BeforeEach
    void setUp() {
        output = new StubOutput();
        tracker = new MemTracker();
        tracker.add(new Item("Deleted item"));
        deleteAction = new DeleteAction(output);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenItemWasDeletedSuccessfully() {
        when(input.askInt(any(String.class))).thenReturn(1);
        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
                        + "Заявка удалена успешно." + ln
        );
    }

    @Test
    public void whenItemWasNotDeleted() {
        when(input.askInt(any(String.class))).thenReturn(2);
        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
                        + "Ошибка удаления заявки." + ln
        );
    }
}