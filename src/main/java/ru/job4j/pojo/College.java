package ru.job4j.pojo;

import java.time.LocalDate;

import java.time.Month;

public class College {

    public static void main(String[] args) {
        Student ivan = new Student();
        ivan.setFullName("Ivanov Ivan Ivanovich");
        ivan.setGroup(3);
        ivan.setReceiptDate(LocalDate.of(2017, Month.SEPTEMBER, 1));
        System.out.println("ФИО студента: " + ivan.getFullName()
                + System.lineSeparator() + "Группа студента: " + ivan.getGroup()
                + System.lineSeparator() + "Дата поступления: " + ivan.getReceiptDate());
    }
}
