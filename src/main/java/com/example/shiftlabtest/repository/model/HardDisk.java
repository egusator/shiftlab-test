package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class HardDisk extends abstractDevice {
    private int capacity;

    public HardDisk(int desktopId, String serialNumber, BigDecimal price, int quantityInStock, int capacity) {
        super(desktopId, serialNumber, price, quantityInStock);
        this.capacity = capacity;
    }

    public HardDisk() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
