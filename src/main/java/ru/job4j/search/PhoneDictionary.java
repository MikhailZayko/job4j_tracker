package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> nameTest = p -> p.getName().contains(key);
        Predicate<Person> surnameTest = p -> p.getSurname().contains(key);
        Predicate<Person> phoneTest = p -> p.getPhone().contains(key);
        Predicate<Person> addressTest = p -> p.getAddress().contains(key);
        var combine = nameTest.or(surnameTest).or(phoneTest).or(addressTest);
        var result = new ArrayList<Person>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}