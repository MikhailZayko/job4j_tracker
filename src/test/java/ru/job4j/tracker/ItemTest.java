package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    public void itemAscByNameTest() {
        List<Item> items = Arrays.asList(
                new Item("SecondItem"),
                new Item("ThirdItem"),
                new Item("FirstItem")
        );
        Collections.sort(items, new ItemAscByName());
        List<Item> expected = Arrays.asList(
                new Item("FirstItem"),
                new Item("SecondItem"),
                new Item("ThirdItem")
        );
        assertThat(items).hasSameElementsAs(expected);
    }

    @Test
    public void itemDescByNameTest() {
        List<Item> items = Arrays.asList(
                new Item("SecondItem"),
                new Item("ThirdItem"),
                new Item("FirstItem")
        );
        Collections.sort(items, new ItemDescByName());
        List<Item> expected = Arrays.asList(
                new Item("ThirdItem"),
                new Item("SecondItem"),
                new Item("FirstItem")
        );
        assertThat(items).hasSameElementsAs(expected);
    }
}