package ru.job4j.oop;

public class Error {

    private boolean active;

    private int status;

    private String message;

    public Error() {

    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Действующая ошибка? - " + active);
        System.out.println("Статус ошибки: " + status);
        System.out.println("Сообщение ошибки: " + message);
    }

    public static void main(String[] args) {
        Error defaultError = new Error();
        defaultError.printInfo();
        Error assertionError = new Error(true, 1, "Утверждение не удалось");
        assertionError.printInfo();
        Error outOfMemoryError = new Error(true, 2, "Закончилась память");
        outOfMemoryError.printInfo();
    }
}
