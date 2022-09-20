package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class HardDisk extends AbstractDevice {
    private int capacity;

    public HardDisk(int desktopId, String serialNumber, BigDecimal price, int quantityInStock,  String manufacturerName,
                    Byte type, int capacity) {
        super();
        this.setDeviceId(desktopId);
        this.setSerialNumber(serialNumber);
        this.setPrice(price);
        this.setQuantityInStock(quantityInStock);

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
