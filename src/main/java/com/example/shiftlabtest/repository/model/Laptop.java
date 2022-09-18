package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class Laptop extends AbstractDevice {
    private Byte size;

    public Laptop(int desktopId, String serialNumber, BigDecimal price,
                   int quantityInStock,  String manufacturerName,  Byte type ,Byte size) {
        super(desktopId, serialNumber, price, quantityInStock,manufacturerName ,type);
        this.size = size;
    }

    public Laptop(UnknownDevice unknownDevice) {
        super(unknownDevice.getDeviceId(),
                unknownDevice.getSerialNumber(),
                unknownDevice.getPrice(),
                unknownDevice.getQuantityInStock(),
                unknownDevice.getManufacturerName(),
                unknownDevice.getType());
        this.size = unknownDevice.getSize();
    }
    public Laptop() {}
    public Byte getFormFactor() {
        return size;
    }

    public Byte getSize() {
        return size;
    }

    public void setSize(Byte size) {
        this.size = size;
    }
}
