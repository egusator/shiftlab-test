package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class Laptop extends AbstractDevice {
    private Byte size;

    public Laptop(int desktopId, String serialNumber, BigDecimal price,
                   int quantityInStock,  Byte type ,Byte size) {
        super(desktopId, serialNumber, price, quantityInStock, type);
        this.size = size;
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
