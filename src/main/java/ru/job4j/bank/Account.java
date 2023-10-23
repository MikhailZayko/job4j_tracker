package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель банковского счета
 * @author MIKHAIL ZAYKO
 * @version 1.0
 */
public class Account {
    /**
     * Поле содержит реквизиты банковского счета
     */
    private String requisite;
    /**
     * Поле содержит баланс банковского счета
     */
    private double balance;

    /**
     * Канонический конструктор
     * @param requisite реквизиты банковского счета
     * @param balance баланс банковского счета
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * Геттер поля requisite
     * @return возвращает реквизиты банковского счета
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * Сеттер поля requisite
     * @param requisite задает реквизиты банковского счета
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * Геттер поля balance
     * @return возвращает баланс банковского счета
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Сеттер поля balance
     * @param balance задает баланс банковского счета
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Метод сравнивает банковские счета по requisite
     * @param o банковский счет с которым идет сравнение
     * @return возвращает true при одинаковых реквизитах,
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
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    /**
     * HashCode банковского счета по полю requisite
     * @return возвращает HashCode банковского счета по реквизитам
     */
    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
