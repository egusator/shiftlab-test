package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class Monitor extends abstractDevice {
    private Short diagonal;

    public Monitor(int desktopId, String serialNumber, BigDecimal price, int quantityInStock, Byte type,
                   Short diagonal) {
        super(desktopId, serialNumber, price, quantityInStock, type);
        this.diagonal = diagonal;
    }

    public Monitor() {
    }

    public Short getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Short diagonal) {
        this.diagonal = diagonal;
    }
}
