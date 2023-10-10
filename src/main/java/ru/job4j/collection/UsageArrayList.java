package ru.job4j.collection;

import java.util.ArrayList;

public class UsageArrayList {

    public static void main(String[] args) {
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("Petr");
        nameList.add("Ivan");
        nameList.add("Stepan");
        for (String name : nameList) {
            System.out.println(name);
        }
    }
}
