package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class HardDisk extends AbstractDevice {
    private int capacity;

    public HardDisk() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
