package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class Monitor extends AbstractDevice {
    private Short diagonal;

    public Monitor(int desktopId, String serialNumber, BigDecimal price, int quantityInStock,  String manufacturerName, Byte type,
                   Short diagonal) {
        super(desktopId, serialNumber, price, quantityInStock, manufacturerName ,type);
        this.diagonal = diagonal;
    }
    public Monitor(UnknownDevice unknownDevice) {
        super(unknownDevice.getDeviceId(),
                unknownDevice.getSerialNumber(),
                unknownDevice.getPrice(),
                unknownDevice.getQuantityInStock(),
                unknownDevice.getManufacturerName(),
                unknownDevice.getType());
        this.diagonal = unknownDevice.getDiagonal();
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
