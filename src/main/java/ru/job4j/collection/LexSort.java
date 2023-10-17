package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftNumber = left.split("\\. Task\\.");
        String[] rightNumber = right.split("\\. Task\\.");
        return Integer.compare(Integer.parseInt(leftNumber[0]), Integer.parseInt(rightNumber[0]));
    }
}