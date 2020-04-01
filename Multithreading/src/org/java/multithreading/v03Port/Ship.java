package org.java.multithreading.v03Port;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ship implements Runnable {
    private int num;
    private long carryingCapacity;

    private List<Container> containers;

    private Port port;

    private Executor executor;

    public Ship(int num, long carryingCapacity, Port port) {
        this.num = num;
        this.carryingCapacity = carryingCapacity;
        this.containers = new ArrayList<>();
        this.port = port;

        for (int i = 1; i < 4; i++) {
            containers.add(new Container(i * num, 100));
        }
        executor = Executors.newSingleThreadScheduledExecutor();
        ((ScheduledExecutorService) executor).scheduleWithFixedDelay(this, 2, 5, TimeUnit.SECONDS);
    }

    public void loadContainers() {
        Container container = null;
        while (currentCapacity() < carryingCapacity) {
            container = port.getContainer();

            if (container == null)
                return;

            if (currentCapacity() + container.getWeight() < carryingCapacity)
                containers.add(container);
            else port.putContainer(container);

            sleep();
            System.out.println("На корабль " + num + " загрузили контейнер " + container.getNum());
        }
    }

    public void unloadContainer(Container container) {
        port.putContainer(container);
        containers.remove(container);

        System.out.println("Из корабля " + num + " разгрузили контейнер " + container.getNum());
    }

    private long currentCapacity() {
        long sum = 0;
        for (Container container : containers)
            sum += container.getWeight();
        return sum;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        port.putShip(this);

        for (int i = 0; i < containers.size(); i++) {
            unloadContainer(containers.get(i));
            //sleep();
        }

        loadContainers();

        port.getShip(this);
        sleep();
    }
}
