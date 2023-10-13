package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("parsentev@yandex.ru", "Petr Arsentev Sergeevich");
        map.put("parsentev@yandex.ru", "Arsentev Petr Sergeevich");
        map.put("ivanivanov@gmail.com", "Ivan Ivanov Ivanovich");
        map.put("ivanivanov@gmail.com", "Ivanov Ivan Ivanovich");
        map.put("vasyapupkin@mail.ru", "Vasya Pupkin Andreevich");
        map.put("vasyapupkin@mail.ru", "Pupkin Vasya Andreevich");
        for (String key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}
