package org.java.multithreading.v01Port;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Port {
    private long capacity;

    private int berths_num;
    private List<Ship> berths;

    private List<Container> containers;

    boolean containerSet = false;

    public Port(long capacity, int berths_num) {
        this.capacity = capacity;
        this.berths_num = berths_num;
        this.berths = new ArrayList<>();
        this.containers = new Vector<>();
        System.out.println("Create port");
    }

    public synchronized void getShip(Ship ship) {
        berths.remove(ship);
        System.out.println("C порта отправился корабль " + ship.getNum());
        notify();
    }

    public synchronized void putShip(Ship ship) {
        while (berths.size() >= berths_num) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(
                        "Исключение типа InterruptedException перехвачено ");
            }
        }
        berths.add(ship);
        System.out.println("В порт приплыл корабль " + ship.getNum());
    }

    public synchronized Container getContainer() {
        while (containerSet){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(
                        "Исключение типа InterruptedException перехвачено ");
            }
        }
        containerSet = true;
        int numContainer = containers.size();
        Container container = numContainer != 0 ? containers.remove(containers.size() - 1) : null;

        if(container!=null)
            System.out.println("Разгружение из порта контейнера " + container.getNum());

        containerSet = false;
        notify();
        return container;
    }

    public synchronized void putContainer(Container container) {
        while (currentCapacity() + container.getWeight() > capacity && containerSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(
                        "Исключение типа InterruptedException перехвачено ");
            }
        }
        containerSet = true;
        containers.add(container);
        System.out.println("Загружение в порт контейнера " + container.getNum());
        containerSet = false;
        notify();
    }

    private long currentCapacity() {
        long sum = 0;
        for (Container container : containers)
            sum += container.getWeight();
        return sum;
    }
}
