package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        int number = 1;
        Input in = new StubInput(
                new String[]{"one", String.valueOf(number)}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(number);
    }

    @Test
    public void whenValidInput() {
        Output out = new StubOutput();
        int number = 2;
        Input in = new StubInput(
                new String[]{String.valueOf(number)}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(number);
    }

    @Test
    public void whenMultipleValidInput() {
        Output out = new StubOutput();
        int firstNumber = 2;
        int secondNumber = 1;
        int thirdNumber = 3;
        Input in = new StubInput(new String[]{
                String.valueOf(firstNumber),
                String.valueOf(secondNumber),
                String.valueOf(thirdNumber)
        });
        ValidateInput input = new ValidateInput(out, in);
        int firstSelected = input.askInt("Enter menu:");
        int secondSelected = input.askInt("Enter menu:");
        int thirdSelected = input.askInt("Enter menu:");
        assertThat(firstSelected).isEqualTo(firstNumber);
        assertThat(secondSelected).isEqualTo(secondNumber);
        assertThat(thirdSelected).isEqualTo(thirdNumber);
    }

    @Test
    public void whenNegativeNumberInput() {
        Output out = new StubOutput();
        int number = -5;
        Input in = new StubInput(
                new String[]{String.valueOf(number)}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected).isEqualTo(number);
    }
}