package org.java.multithreading.v04Port;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Port {
    private long capacity;

    private int berths_num;
    private List<Ship> berths;
    private Semaphore semaphore;
    private final ReadWriteLock readWriteLockShips = new ReentrantReadWriteLock();
    private final Lock readLockShips = readWriteLockShips.readLock();
    private final Lock writeLockShips = readWriteLockShips.writeLock();

    private List<Container> containers;
    boolean containerSet = false;
    private final ReadWriteLock readWriteLockContainers = new ReentrantReadWriteLock();
    private final Lock readLockContainers = readWriteLockContainers.readLock();
    private final Lock writeLockContainers = readWriteLockContainers.writeLock();

    public Port(long capacity, int berths_num) {
        this.capacity = capacity;
        this.berths_num = berths_num;
        this.berths = new ArrayList<>();
        this.containers = new ArrayList<>();
        this.semaphore = new Semaphore(berths_num, true);
        System.out.println("Create port");
    }

    public void getShip(Ship ship) {
        readLockShips.lock();
        try
        {
            berths.remove(ship);
            System.out.println("C порта отправился корабль " + ship.getNum());
        }
        finally
        {
            readLockShips.unlock();
        }

        semaphore.release();
    }

    public void putShip(Ship ship) {
        writeLockShips.lock();
        try
        {
            semaphore.acquire();
            berths.add(ship);
            System.out.println("В порт приплыл корабль " + ship.getNum());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally
        {
            writeLockShips.unlock();
        }
    }

    public synchronized Container getContainer() {
        readLockContainers.lock();
        try
        {
            int numContainer = containers.size();
            Container container = numContainer != 0 ? containers.remove(containers.size() - 1) : null;

            if (container != null)
                System.out.println("Разгружение из порта контейнера " + container.getNum());
            notify();
            return container;
        }
        finally
        {
            readLockContainers.unlock();
        }
    }

    public synchronized void putContainer(Container container) {
        while (currentCapacity() + container.getWeight() > capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(
                        "Исключение типа InterruptedException перехвачено ");
            }
        }
        writeLockContainers.lock();
        try
        {
            containers.add(container);
            System.out.println("Загружение в порт контейнера " + container.getNum());
        }
        finally
        {
            writeLockContainers.unlock();
        }
    }

    private long currentCapacity() {
        long sum = 0;
        for (Container container : containers)
            sum += container.getWeight();
        return sum;
    }
}
