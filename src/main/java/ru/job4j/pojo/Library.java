package ru.job4j.pojo;

public class Library {

    public static void main(String[] args) {
        Book warAndPeace = new Book("War and piece", 1300);
        Book cleanCode = new Book("Clean code", 100);
        Book deadSouls = new Book("Dead souls", 352);
        Book headFirst = new Book("Head first Java", 720);
        Book[] books = {warAndPeace, cleanCode, deadSouls, headFirst};
        for (Book book : books) {
            System.out.println("Название книги: " + book.getName() + ", количество страниц - " + book.getNumberOfPages());
        }
        books[0] = headFirst;
        books[3] = warAndPeace;
        for (Book book : books) {
            System.out.println("Название книги: " + book.getName() + ", количество страниц - " + book.getNumberOfPages());
        }
        for (Book book : books) {
            if ("Clean code".equals(book.getName())) {
                System.out.println("Название книги: " + book.getName() + ", количество страниц - " + book.getNumberOfPages());
            }
        }
    }
}
