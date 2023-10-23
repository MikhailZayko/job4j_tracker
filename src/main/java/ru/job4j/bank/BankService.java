package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу банковского сервиса через
 * ассоциативную карту пользователей системы
 * @author MIKHAIL ZAYKO
 * @version 1.0
 */
public class BankService {
    /**
     * Поле содержит всех пользователей системы с привязанными к ним счетами
     * в коллекции типа Map, где ключ - это пользователь банка,
     * а значение - список банковских счетов этого пользователя
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает пользователя и добавляет его в ассоциативную карту.
     * Если в карте уже имеется данный пользователь, то он не будет добавлен
     * @param user пользователь, который добавляется в карту
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод принимает паспорт пользователя и удаляет этого пользователя из ассоциативной карты
     * @param passport паспорт пользователя, по которому будет найден этот пользователь
     */
    public void deleteUser(String passport) {
        users.remove(new User(passport, ""));
    }

    /**
     * Метод добавляет новый счет к пользователю, принимая пасспорт пользователя и сам счет.
     * Если такой пользователь не найден, то метод ничего не делает.
     * Если такой счет уже существует у пользователя, то он не будет добавлен
     * @param passport паспорт пользователя, по которому будет найден этот пользователь
     * @param account банковский счет, который будет добавлен в список счетов пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAccounts = users.get(user);
            if (!userAccounts.contains(account)) {
                userAccounts.add(account);
            }
        }
    }

    /**
     * Метод ищет пользователя по номеру паспорта, принимя паспорт
     * @param passport паспорт пользователя, по которому будет найден этот пользователь
     * @return возвращает пользователя или null если пользователь не найден
     */
    public User findByPassport(String passport) {
        User result = null;
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                result = user;
                break;
            }
        }
        return result;
    }

    /**
     * Метод ищет счет пользователя по реквизитам,
     * принимая номер паспорта и реквизиты счета
     * @param passport паспорт пользователя, по которому будет найден этот пользователь
     * @param requisite реквизиты счета, по которым будет найдет счет пользователя
     * @return возвращает счет пользователя или null если пользователь или реквизиты не найдены
     */
    public Account findByRequisite(String passport, String requisite) {
        Account result = null;
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAccounts = users.get(user);
            for (Account account : userAccounts) {
                if (requisite.equals(account.getRequisite())) {
                    result = account;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод перечисляет деньги с одного счёта на другой счёт
     * @param srcPassport паспорт пользователя, с счета которого будут переводиться деньги
     * @param srcRequisite реквизиты счета с которого будут переводиться деньги
     * @param destPassport паспорт пользователя, на счет которого будут переводиться деньги
     * @param destRequisite реквизиты счета на который будут переводиться деньги
     * @param amount сумма перевода
     * @return возвращает true в случае успешного перечисления. В случае если один из счетов
     * не найден или на счете с которого переводят деньги недостаточно средств возвращает false
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод принимает пользователя и позволяет полуить его банковские счета
     * @param user пользователь - ключ по которому будет осуществляться
     * поиск его банковских счетов
     * @return возвращает список банковских счетов или null в случае отсутствия
     * пользователя в карте
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}