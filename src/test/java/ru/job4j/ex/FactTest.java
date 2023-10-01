package ru.job4j.ex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactTest {
    @Test
    public void whenException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Fact().calc(-5);
                });
        assertThat(exception.getMessage()).isEqualTo("N could not be less than 0");
    }

    @Test
    public void when4Then24() {
        int in = 4;
        int expected = 24;
        int out = new Fact().calc(in);
        assertThat(out).isEqualTo(expected);
    }
}