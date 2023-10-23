package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель пользователя банка
 * @author MIKHAIL ZAYKO
 * @version 1.0
 */
public class User {
    /**
     * Поле содержит номер пасспорта пользователя банка
     */
    private String passport;
    /**
     * Поле содержит ФИО пользователя банка
     */
    private String username;

    /**
     * Канонический конструктор
     * @param passport номер пасспорта пользователя банка
     * @param username ФИО пользователя банка
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * Геттер поля passport
     * @return возвращает номер пасспорта пользователя банка
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Сеттер поля passport
     * @param passport задает номер пасспорта пользователя банка
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Геттер поля username
     * @return возвращает ФИО пользователя банка
     */
    public String getUsername() {
        return username;
    }

    /**
     * Сеттер поля username
     * @param username задает ФИО пользователя банка
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Метод сравнивает пользователей банка по passport
     * @param o пользователь банка с которым идет сравнение
     * @return возвращает true при одинаковыъ паспортах,
     * иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    /**
     * HashCode пользователя банка по полю passport
     * @return возвращает HashCode пользователя банка по паспорту
     */
    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
