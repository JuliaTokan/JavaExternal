package org.java.multithreading.v01Port;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Port port = new Port(1000, 2);

        Ship ship1 = new Ship(1, 350, port);
        Ship ship2 = new Ship(4, 350, port);
        Ship ship3 = new Ship(8, 350, port);
    }
}
