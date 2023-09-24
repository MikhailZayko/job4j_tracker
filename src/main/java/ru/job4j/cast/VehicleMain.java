package ru.job4j.cast;

public class VehicleMain {

    public static void main(String[] args) {
        Vehicle airplane = new Airplane();
        Vehicle train = new Train();
        Vehicle bus = new Bus();
        Vehicle[] vehicles = {train, bus, airplane};
        for (Vehicle v : vehicles) {
            v.move();
        }
    }
}
