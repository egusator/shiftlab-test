package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public abstract class abstractDevice {
    private int deviceId;
    private String serialNumber;
    private BigDecimal price;
    private int quantityInStock;

    public abstractDevice(int desktopId, String serialNumber, BigDecimal price, int quantityInStock) {
        this.deviceId = desktopId;
        this.serialNumber = serialNumber;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public abstractDevice() {
    }
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
