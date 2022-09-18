package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class HardDisk extends AbstractDevice {
    private int capacity;

    public HardDisk(int desktopId, String serialNumber, BigDecimal price, int quantityInStock,  String manufacturerName,
                    Byte type, int capacity) {
        super(desktopId, serialNumber, price, quantityInStock, manufacturerName ,type);
        this.capacity = capacity;
    }

    public HardDisk(UnknownDevice unknownDevice) {
        super(unknownDevice.getDeviceId(),
                unknownDevice.getSerialNumber(),
                unknownDevice.getPrice(),
                unknownDevice.getQuantityInStock(),
                unknownDevice.getManufacturerName(),
                unknownDevice.getType());
        this.capacity = unknownDevice.getCapacity();
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
