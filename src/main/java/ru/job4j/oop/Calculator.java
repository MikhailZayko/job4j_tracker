package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int a) {
        return x + a;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int a) {
        return a - x;
    }

    public int divide(int a) {
        return a / x;
    }

    public int sumAllOperation(int a) {
        return sum(a) + multiply(a) + minus(a) + divide(a);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int sumResult = sum(30);
        int multiplyResult = calculator.multiply(30);
        int minusResult = minus(30);
        int divideResult = calculator.divide(30);
        int sumAllResult = calculator.sumAllOperation(30);
        System.out.println("If argument = 30:"
                + System.lineSeparator() + "sum = " + sumResult
                + System.lineSeparator() + "multiply = " + multiplyResult
                + System.lineSeparator() + "minus = " + minusResult
                + System.lineSeparator() + "divide = " + divideResult
                + System.lineSeparator() + "sumAllOperation = " + sumAllResult);
    }
}
