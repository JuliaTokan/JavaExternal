package org.java.multithreading.v02Port;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Port port = new Port(1000, 3);

        Ship ship1 = new Ship(1, 350, port);
        Ship ship2 = new Ship(4, 350, port);
        Ship ship3 = new Ship(8, 350, port);
        Ship ship4 = new Ship(12, 350, port);
        Ship ship5 = new Ship(16, 350, port);
    }
}
