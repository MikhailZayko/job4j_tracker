package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Автобус едет");
    }

    @Override
    public void passengers(int passengers) {
        System.out.println("Автобус вместил пассажиров: " + passengers);
    }

    @Override
    public double refuel(double fuel) {
        return fuel * 61.22;
    }
}
